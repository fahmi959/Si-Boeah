# Si-Boeah Machine Learning Capstone Product
This repository contains the machine learning models developed for the Si-Boeah Capstone Product. The project consists of two main models: object detection using YOLOv5 and classification using two different approaches - transfer learning and non-transfer learning. Each model is accompanied by an IPython Notebook (.ipynb) file and an .h5 file to store the trained model.

## Object Detection using YOLOv5
The YOLOv5 model is a state-of-the-art object detection model known for its real-time performance and accuracy. This model is trained to detect and localize objects of interest in images. The trained model is stored in the .h5 file. ... .pt

## Classification Models
### Transfer Learning Approach
The transfer learning approach leverages pre-trained models that have been trained on large-scale datasets. By utilizing the knowledge learned from these datasets, the transfer learning model can achieve better performance even with limited training data. The transfer learning model is implemented in the data_understanding_capstone.ipynb notebook file. The trained model is saved in the model_v1.2.0.h5 file.

### Non-Transfer Learning Approach
The non-transfer learning approach involves training the model from scratch using the Si-Boeah dataset. This approach is suitable when the dataset is sufficiently large and representative of the target problem. The non-transfer learning model is implemented in two version, the first version named capstone_non-TL.ipynb notebook file. The trained model is stored in the .h5 file that can be downloaded in google drive link with the instruction bellow this paragraph. Then, the last version of the non-transfer learning model is named capstone_non-TLV2.ipynb notebook file. The trained model is stored in the model_nonTL_v2.1.0.h5 file.


**Download Non-Transfer Learning Model V1 (H5 File)**


To utilize the non-transfer learning model v1 for machine learning, you can download the H5 file from the following link:

[Download Non-Transfer Learning Model v1 (H5 File)](https://drive.google.com/file/d/1y65ZsoKOobvUhQcnxJaQiFPRQafo22Vs/view?usp=sharing)

Make sure to save the H5 file in the appropriate directory to access it in your code.
Please note that this model is specifically designed for non-transfer learning tasks and may require customization based on your specific use case.
If you have any questions or need further assistance, feel free to reach out.

## Dataset
The dataset used for training and evaluation in this project is not included in this repository due to its large size. However, instructions on how to obtain and preprocess the dataset can be found in the main page of Si-Boeah README.md files.

## Dependencies
To run the notebooks and utilize the models, make sure you have the following dependencies installed:

- Python 3.x
- TensorFlow
- PyTorch
- OpenCV
- NumPy
- Matplotlib
- Jupyter Notebook

Please refer to the individual notebook files for specific library versions and installation instructions.

## Usage
To use the models, follow the instructions provided in the respective notebook files. The notebooks contain detailed explanations and code snippets to guide you through the process of training, evaluation, and inference using the models.

## License
The code and models in this repository are provided under the Open Source License.

Feel free to explore and utilize the Si-Boeah Machine Learning Capstone Product for your own projects or research purposes. If you have any questions or suggestions, please feel free to reach out.

Happy machine learning!
