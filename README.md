## Step -1 Start Docker Desktop Application

## Step -2 create docker-compose.yml file in your project root directory with below content

```
services:
  kafka:
    image: apache/kafka:3.9.0
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: 'broker,controller'
      # We add 'INTERNAL' to the map so Kafka knows it's a safe connection
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: 'CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT,INTERNAL:PLAINTEXT'
      KAFKA_CONTROLLER_QUORUM_VOTERS: '1@localhost:9093'
      KAFKA_LISTENERS: 'PLAINTEXT://:9092,CONTROLLER://:9093,INTERNAL://:29092'
      # Advertised listeners: localhost for your Windows apps, kafka for Docker apps
      KAFKA_ADVERTISED_LISTENERS: 'PLAINTEXT://localhost:9092,INTERNAL://kafka:29092'
      KAFKA_CONTROLLER_LISTENER_NAMES: 'CONTROLLER'
      KAFKA_INTER_BROKER_LISTENER_NAME: 'INTERNAL'
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_CLUSTER_ID: 'Nc7_X37mSCuS_A7pGpx8mw'

  kafka-ui:
    image: provectuslabs/kafka-ui:v0.7.2
    container_name: kafka-ui
    ports:
      - "8080:8080"
    environment:
      KAFKA_CLUSTERS_0_NAME: local-cluster
      # The UI now uses the INTERNAL listener port
      KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:29092
    depends_on:
      - kafka
```

## Step 3 - Open Terminal and start docker images using below commands

```
    docker compose up -d
```

## Step 4 - Now Open Web Browser and access Kafka UI using below URL

```
    http://localhost:8080
```
# Enjoy Coding!