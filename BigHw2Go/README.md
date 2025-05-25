# Описание архитектуры:
Есть три микросервиса - apigateway, filesstoring, fileanalysis \
Все запросы идут в apigateway и далее перенаправляются в остальные два сервиса. \
Директории в которых запускаются серверы и настраивается маршрутизация:
- internal/infrastructure/apigatewayserver
- internal/infrastructure/filesstoringapi
- internal/infrastructure/fileanalysisapi

В папеке internal/domain лежат все доменные модели \
Далее все основное расположено в internal/infrastructure \
В директории repository - находятся репозитории которые работают напрямую с бд, делают конкретный запрос и отправляют результат. \
Например добавить новый файл, добавить новый анализ и тд.
В директории clients находятся клиенты для общения между микросервисами (clients/api), а также для внешней апи (clients/wordcloudapi) \
Директория usecases: \
fileusecase и analysisusecase это юзкейсы для работы с репозиториями - они обьединяют несколько запросов в бд если это нужно, оборачивают контексты и тд \
clientfilesstoring и wordcloudapiusecase - это обертка над клиентами для общения между микросервисами и общения с внешней апи. \
filesstorageusecase - отвечает за работу с файловой системой - то есть может создать новый файл, отдать файл по локации. \
fileanalysisfacade и filesstoring - фасады которые реализуют какой то конкретный запрос например загрузить новый файл или провести его анализ \
fileanalyzer - принимает io.Reader и возвращает сущность в которой есть информация об анализе файла \
В итоге запрос выглядит примерно так:
uploadfile -> apigateway -> filesstoringapi -> uploadfilehandler -> filesstoringfacade -> (filesusecase - filesrepository), (filesstorageusecase)

# Спецификация API
- Загрузка файла\
curl -X POST http://127.0.0.1:8080/file/storage/upload -F "file=@toload/bigfile.txt"
Можно подставить какой-то свой файл вместо bigfile.txt, я решил что пусть все файлы которые мы хотим подгружать будут лежать в папке toload \
Запрос возвращает uuid файла со статусом OK либо с плохим статусом и информации об ошибке\
Сохраняет файл он в папку uploaded/{current_time}/{filename}
- Анализ файла\
curl -X GET http://127.0.0.1:8080/file/analysis/{id}
Подставляем id который нам вернулся при первом запросе\
Запрос возвращает структуру которая содержит информацию про анализ файла(число слов, абзацев, символов), id файла(тот же), локацию где находиться файл с облаком слов\
Сохраняет облако слов в wordcloudapiimages/{current_time}, соответственно эту локацию и возвращает.
- Получение файла\
curl -X GET http://127.0.0.1:8080/file/storage/getfile/{id}
Подставляем id вернувшийся из первого запроса\
Запрос возвращает содержимое файла
- Получение облака слов\
curl -X GET http://127.0.0.1:8080/file/analysis/wordcloud -F "location={location}"
Подставляем вместо {location} локацию вернувшеюся нам из второго запроса. И да curl видимо не может выполнить такой запрос так как приходит png файл, поэтому можно выполнить в postman таким запросом - http://127.0.0.1:8080/file/analysis/wordcloud?location={location} \
Запрос возвращает файл с облком слов.

# Запуск программы
Чтобы запустить базы данных: docker compose -f .\docker-deploy\docker-compose.yml up \
Далее запустить три программы из cmd