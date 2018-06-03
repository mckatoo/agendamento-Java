#!/bin/bash

docker run -d \
--name=db \
--restart=on-failure:3 \
--network host \
postgres
