FROM python:3.8.5-alpine3.12
LABEL maintainer="struti.alex69@gmail.com"

RUN apk add libxml2-dev libxslt-dev python3-dev gcc build-base
RUN pip install lxml

COPY cover2cover.py /opt/cover2cover.py
COPY source2filename.py /opt/source2filename.py
