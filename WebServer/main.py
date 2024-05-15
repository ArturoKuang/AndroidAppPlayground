from fastapi import FastAPI
from fastapi.responses import HTMLResponse

app = FastAPI()
health_data = [
    {
        "id": 1,
        "name": "John Doe",
        "age": 30,
        "weight": 70,
        "height": 175,
        "conditions": ["hypertension", "diabetes"]
    },
    {
        "id": 2,
        "name": "Jane Smith",
        "age": 25,
        "weight": 60,
        "height": 165,
        "conditions": ["asthma"]
    },
    {
        "id": 3,
        "name": "Alice Johnson",
        "age": 35,
        "weight": 80,
        "height": 160,
        "conditions": ["obesity", "hypothyroidism"]
    },
    {
        "id": 4,
        "name": "Bob Brown",
        "age": 40,
        "weight": 90,
        "height": 180,
        "conditions": ["high cholesterol"]
    },
    {
        "id": 5,
        "name": "Eve Davis",
        "age": 29,
        "weight": 55,
        "height": 170,
        "conditions": ["anemia"]
    }
]

@app.get("/list")
async def read_list():
    return health_data
    
@app.get("/", response_class=HTMLResponse)
async def read_root():
    return """
    <html>
        <head>
            <title>Home</title>
        </head>
        <body>
            <h1>Home</h1>
            <p>Welcome to the home page!</p>
        </body>
    </html>
    """
