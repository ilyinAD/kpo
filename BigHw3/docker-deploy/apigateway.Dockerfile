FROM golang:1.24-alpine AS builder
LABEL authors="artem"
WORKDIR /build

COPY ../go.mod .
COPY ../go.sum .
RUN go mod tidy

COPY .. .

RUN go build -o apigateway.exe ./cmd/apigateway

FROM alpine:latest AS runtime

RUN mkdir -p /build/docker-deploy
COPY ./docker-deploy/.env /build/docker-deploy/.env

COPY --from=builder /build/apigateway.exe /bin/apigateway.exe

WORKDIR /build

CMD ["/bin/apigateway.exe"]
