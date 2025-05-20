package domainfilesstoringserver

import "github.com/google/uuid"

type FileModel struct {
	ID       uuid.UUID
	Name     string
	Location string
}

func NewFileModel(name, location string, f func() uuid.UUID) *FileModel {
	return &FileModel{
		ID:       f(),
		Name:     name,
		Location: location,
	}
}
