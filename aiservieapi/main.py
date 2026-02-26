from flask import Flask, request, jsonify
import tensorflow as tf
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image
import numpy as np
import os
from io import BytesIO

app = Flask(__name__)


MODEL_PATH = os.path.join(os.path.dirname(__file__), "trained_model1.h5")
model = load_model(MODEL_PATH)

# model warmup
dummy = np.zeros((1,150,150,3), dtype=np.float32)
model.predict(dummy)

print("DONE WARMUP")



@app.route('/api/ai/analyze', methods=['POST'])
def upload_file():

    sent_from = request.headers.get("Sent-From")
    auth_header = request.headers.get("Authorization")
    if not auth_header or not auth_header.startswith("Bearer "):
        return jsonify({"error": "Unauthorized"}), 401  

    if 'file' not in request.files:
        return jsonify({"error": "No file provided"}), 400

    file = request.files['file']
    description = request.form.get('description', '')



    # resize 150x150
    img = image.load_img(BytesIO(file.read()), target_size=(150, 150))
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    img_array = img_array / 255.0






    predictions = model.predict(img_array)
    probability = float(np.max(predictions))
    predicted_class_index = int(np.argmax(predictions))

    class_labels = ["Bacterial Leaf Blight", "Brown Spot", "Healthy Rice Leaf", "Leaf Blast", "Leaf scald", "Sheath Blight"]
    disease_name = class_labels[predicted_class_index]

    response = {
        "diseaseName": disease_name,
        "probability": probability
    }

    return jsonify(response), 200


if __name__ == '__main__':
    app.run(debug=True, port=5000)
