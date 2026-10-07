from fastapi import FastAPI
from confluent_kafka import Producer
import json

app = FastAPI()

producer = Producer({
    "bootstrap.servers": "localhost:9092"
})


@app.get("/health")
def health():
    return {
        "status": "UP"
    }


@app.post("/orders", status_code=202)
def create_order(order: dict):
    producer.produce(
        "orders.incoming",
        value=json.dumps(order)
    )

    return {
        "status": "accepted"
    }
