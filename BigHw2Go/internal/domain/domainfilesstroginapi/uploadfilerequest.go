package domainfilesstroginapi

import (
	"mime/multipart"
)

type UploadFileRequest struct {
	file     *multipart.File
	location string
}

func NewUploadFileRequest(f *multipart.File, location string) *UploadFileRequest {
	return &UploadFileRequest{file: f, location: location}
}
