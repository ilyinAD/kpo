package usecases

import (
	"BigHw2Go/internal/domain/domainfileanalysisserver"
	"bufio"
	"io"
	"unicode"
)

type FileAnalyzer struct {
}

func NewFileAnalyzer() *FileAnalyzer {
	return &FileAnalyzer{}
}

func (fa *FileAnalyzer) MakeAnalysis(reader io.Reader, analysisModel *domainfileanalysisserver.FileAnalysisModel) error {
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
