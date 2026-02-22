from flask import Flask, request, jsonify
import tensorflow as tf
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image
import numpy as np
import os

app = Flask(__name__)

# Load the trained model once at startup
MODEL_PATH = os.path.join(os.path.dirname(__file__), "trained_model1.h5")
model = load_model(MODEL_PATH)

@app.route('/upload', methods=['POST'])
def upload_file():
    # Validate headers (optional, matches your Spring Boot service)
    sent_from = request.headers.get("Sent-From")
    auth_header = request.headers.get("Authorization")
    if not auth_header or not auth_header.startswith("Bearer "):
        return jsonify({"error": "Unauthorized"}), 401

    # Get file and description
    if 'file' not in request.files:
        return jsonify({"error": "No file provided"}), 400

    file = request.files['file']
    description = request.form.get('description', '')

    # Preprocess image: resize to 150x150, 3 channels
    img = image.load_img(file, target_size=(150, 150))  # PIL image
    img_array = image.img_to_array(img)                 # convert to numpy array
    img_array = np.expand_dims(img_array, axis=0)       # add batch dimension
    img_array = img_array / 255.0                       # normalize

    # Run prediction
    predictions = model.predict(img_array)
    probability = float(np.max(predictions))            # highest probability
    predicted_class_index = int(np.argmax(predictions)) # class index

    # Map index to disease name (example mapping, adjust to your dataset)
    class_labels = ["Rice Blast", "Brown Spot", "Healthy"]
    disease_name = class_labels[predicted_class_index]

    # Build response aligned with AiResponseDto
    response = {
        "diseaseName": disease_name,
        "probability": probability
    }

    return jsonify(response), 200


if __name__ == '__main__':
    app.run(debug=True, port=5000)
