FROM golang:1.24-alpine AS builder
LABEL authors="artem"
WORKDIR /build

COPY go.mod go.sum ./
RUN go mod tidy

COPY .. .

RUN go build -o payment.exe ./cmd/payment

FROM alpine:latest AS runtime

RUN mkdir -p /build/docker-deploy
COPY ./docker-deploy/.env /build/docker-deploy/.env

COPY --from=builder /build/payment.exe /bin/payment.exe

WORKDIR /build

CMD ["/bin/payment.exe"]
