package domainfileanalysisserver

import (
	"github.com/google/uuid"
)

type FileAnalysisModel struct {
	ID           uuid.UUID
	ParagraphCnt int64
	WordCnt      int64
	SymbolCnt    int64
	ImgLocation  string
}

func NewFileAnalysisModel(paragrathCnt, wordCnt, SymbolCnt int64, imgLocation string, id uuid.UUID) *FileAnalysisModel {
	return &FileAnalysisModel{
		ID:           id,
		ParagraphCnt: paragrathCnt,
		WordCnt:      wordCnt,
		SymbolCnt:    SymbolCnt,
		ImgLocation:  imgLocation,
	}
}
