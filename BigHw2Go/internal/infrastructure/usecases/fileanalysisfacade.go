package usecases

import (
	"BigHw2Go/internal/domain/domainfileanalysisserver"
	"bufio"
	"context"
	"errors"
	"fmt"
	"io"
	"log"
	"time"
	"unicode"

	"github.com/google/uuid"
)

type FileAnalysisFacade struct {
	clientFilesStoring  *ClientFilesStoring
	analysisUseCase     *AnalysisUseCase
	filesStorageUseCase *FilesStorageUseCase
	wordCloudAPIUseCase *WordCloudAPIUseCase
}

func NewFileAnalysisFacade(clientFilesStoring *ClientFilesStoring, analysisUseCase *AnalysisUseCase,
	filesStorageUseCase *FilesStorageUseCase, wordCloudAPIUseCase *WordCloudAPIUseCase) *FileAnalysisFacade {
	return &FileAnalysisFacade{
		clientFilesStoring:  clientFilesStoring,
		analysisUseCase:     analysisUseCase,
		filesStorageUseCase: filesStorageUseCase,
		wordCloudAPIUseCase: wordCloudAPIUseCase,
	}
}

func (fa *FileAnalysisFacade) makeAnalysis(reader io.ReadCloser, analysisModel *domainfileanalysisserver.FileAnalysisModel) error {
	bufReader := bufio.NewReader(reader)
	var inWord bool

	for {
		line, err := bufReader.ReadString('\n')

		allSpaceSymbol := true

		for _, r := range string(line) {
			analysisModel.SymbolCnt++

			if unicode.IsSpace(r) {
				inWord = false
			} else {
				allSpaceSymbol = false

				if !inWord {
					analysisModel.WordCnt++
					inWord = true
				}
			}
		}

		if allSpaceSymbol {
			analysisModel.ParagraphCnt++
		}

		if err != nil {
			if err == io.EOF {
				break
			}

			return err
		}
	}

	return nil
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

	imgReader, err := fa.wordCloudAPIUseCase.GetImage(reader)
	if err != nil {
		return nil, fmt.Errorf("wordCloudAPIUseCase: GetImage: %w", err)
	}

	location := "wordcloudapiimages/" + time.Now().Format("20060102_150405")

	err = fa.filesStorageUseCase.UploadFileToStorage(imgReader, location)
	if err != nil {
		return nil, fmt.Errorf("filesStorageUseCase: UploadFileToStorage: %w", err)
	}

	fileAnalysisModel = domainfileanalysisserver.NewFileAnalysisModel(0, 0, 0, location, fileID)

	err = fa.makeAnalysis(reader, fileAnalysisModel)
	if err != nil {
		return nil, fmt.Errorf("makeAnalysis: %w", err)
	}

	addedFileAnalysisModel, err := fa.analysisUseCase.AddAnalysis(ctx, fileAnalysisModel)
	if err != nil {
		return nil, fmt.Errorf("analysisUseCase: AddAnalysis: %w", err)
	}

	return addedFileAnalysisModel, nil
}
