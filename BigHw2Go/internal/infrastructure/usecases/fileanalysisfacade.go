package usecases

import (
	"BigHw2Go/internal/domain/domainfileanalysisserver"
	"context"
	"errors"
	"fmt"
	"io"
	"log"
	"strings"
	"time"

	"github.com/google/uuid"
)

type FileAnalysisFacade struct {
	clientFilesStoring  *ClientFilesStoring
	analysisUseCase     *AnalysisUseCase
	filesStorageUseCase *FilesStorageUseCase
	wordCloudAPIUseCase *WordCloudAPIUseCase
	fileAnalyzer        *FileAnalyzer
}

func NewFileAnalysisFacade(clientFilesStoring *ClientFilesStoring, analysisUseCase *AnalysisUseCase,
	filesStorageUseCase *FilesStorageUseCase, wordCloudAPIUseCase *WordCloudAPIUseCase, fileAnalyzer *FileAnalyzer) *FileAnalysisFacade {
	return &FileAnalysisFacade{
		clientFilesStoring:  clientFilesStoring,
		analysisUseCase:     analysisUseCase,
		filesStorageUseCase: filesStorageUseCase,
		wordCloudAPIUseCase: wordCloudAPIUseCase,
		fileAnalyzer:        fileAnalyzer,
	}
}

func (fa *FileAnalysisFacade) GetFileAnalysis(ctx context.Context, fileID uuid.UUID) (*domainfileanalysisserver.FileAnalysisModel, error) {
	fileAnalysisModel, err := fa.analysisUseCase.GetByID(ctx, fileID)
	if err != nil {
		return nil, fmt.Errorf("analysisUseCase: GetByID: %w", err)
	}

	if fileAnalysisModel != nil {
		log.Printf("analysis with id: %v wad made before\n", fileID)
		return fileAnalysisModel, nil
	}

	reader, err := fa.clientFilesStoring.GetFile(fileID)
	if err != nil {
		return nil, fmt.Errorf("clientFilesStoring: GetFile: %w", err)
	}

	defer func(reader io.ReadCloser) {
		cErr := reader.Close()
		if cErr != nil {
			if err == nil {
				err = cErr
			}

			err = errors.Join(err, cErr)
		}
	}(reader)

	text, err := io.ReadAll(reader)
	if err != nil {
		return nil, fmt.Errorf("io.ReadAll: %w", err)
	}

	imgReader, err := fa.wordCloudAPIUseCase.GetImage(string(text))
	if err != nil {
		return nil, fmt.Errorf("wordCloudAPIUseCase: GetImage: %w", err)
	}

	location := "wordcloudapiimages/" + time.Now().Format("20060102_150405") + ".png"

	err = fa.filesStorageUseCase.UploadFileToStorage(imgReader, location)
	if err != nil {
		return nil, fmt.Errorf("filesStorageUseCase: UploadFileToStorage: %w", err)
	}

	fileAnalysisModel = domainfileanalysisserver.NewFileAnalysisModel(0, 0, 0, location, fileID)

	err = fa.fileAnalyzer.MakeAnalysis(strings.NewReader(string(text)), fileAnalysisModel)
	if err != nil {
		return nil, fmt.Errorf("makeAnalysis: %w", err)
	}

	addedFileAnalysisModel, err := fa.analysisUseCase.AddAnalysis(ctx, fileAnalysisModel)
	if err != nil {
		return nil, fmt.Errorf("analysisUseCase: AddAnalysis: %w", err)
	}

	return addedFileAnalysisModel, nil
}

func (fa *FileAnalysisFacade) GetWordCloudImg(location string) (io.ReadCloser, error) {
	return fa.filesStorageUseCase.GetFileByLocation(location)
}
