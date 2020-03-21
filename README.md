# log-transfer

## Sender

```bash
/usr/lib/jvm/java-1.6.0-openjdk.x86_64/bin/java -jar log-transfer-1.0.jar
```

## Receiver

### Log4j1

```bash
java -cp ~/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar org.apache.log4j.net.SimpleSocketServer 9500 log4j/log4j.xml
```

### Fluentd

```bash
docker run -d --rm -p 24224:24224 -p 24224:24224/udp fluent/fluentd:v1.9.3-1.0
```

### Logstash

参考
- https://github.com/agorski/logstash-log4j-example

```bash
docker pull logstash:7.6.1
docker run -it --rm -p 9500:9500 logstash:7.6.1 bash
logstash-plugin install logstash-input-log4j
cd /usr/share/logstash/config
vi log4j.conf
logstash -f log4j.conf
```

```
input {
    log4j {
        mode => "server"
        port => 9500
    }
}

output {
    stdout {
    }
}
```


