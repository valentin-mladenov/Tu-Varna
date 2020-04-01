import pandas as pd
import numpy as np

# load MATLAB files
from scipy.io import loadmat

data = loadmat('data.mat')
data.keys()

y = data['y']
# Add intercept
X = np.c_[np.ones((data['X'].shape[0], 1)), data['X']]

print('X:', X.shape, '(with intercept)')
print('y:', y.shape)

weights = loadmat('weights.mat')
weights.keys()

theta1, theta2 = weights['Theta1'], weights['Theta2']
print('theta1 :', theta1.shape)
print('theta2 :', theta2.shape)
params = np.r_[theta1.ravel(), theta2.ravel()]
print('params :', params.shape)


def sigmoid(z):
    return 1 / (1 + np.exp(-z))


def sigmoid_gradient(z):
    # Задача 1
    sigmoid_in = sigmoid(z)
    return sigmoid_in * (1 - sigmoid_in)


def nn_cost_function(nn_params, input_layer_size, hidden_layer_size, num_labels, features, classes, reg):

    # When comparing to Octave code note that Python uses zero-indexed arrays.
    # But because Numpy indexing does not include the right side, the code is the same anyway.
    theta_1 = nn_params[0:(hidden_layer_size * (input_layer_size + 1))].reshape(hidden_layer_size, (input_layer_size + 1))
    theta_2 = nn_params[(hidden_layer_size * (input_layer_size + 1)):].reshape(num_labels, (hidden_layer_size + 1))

    m = features.shape[0]
    y_matrix = pd.get_dummies(classes.ravel()).to_numpy().T
    # y_matrix = np.asmatrix(pd.get_dummies(classes.ravel()))

    # Изчисляване на стойностите във върховете на мрежата (feedforward propagation)
    # за получаване на цената
    # Задача 2 - стойности на върховете в слой 1 (входен слой)
    a1 = features                                                       # 5000x401

    # Задача 2 - стойност z2 във втори слой
    z2 = np.dot(theta_1, a1.transpose())                                # 25x401 * 401x5000 = 25x5000
    a2 = np.c_[np.ones((features.shape[0], 1)), sigmoid(z2.T)]          # 5000x26

    # Задача 2 - стойност z3 в слой 3 (изходен слой)
    z3 = np.dot(theta_2, a2.transpose())                                # 10x26 * 26x5000 = 10x5000

    # Задача 2 - стойност на върха a3 в слой 3 (изходен слой)
    a3 = sigmoid(z3.T)                                                  # 10x5000 (5000x10)

    J = -1 * (1/m) * np.sum(np.log(a3.T) * y_matrix + np.log(1 - a3).T * (1 - y_matrix)) + \
        (reg/(2*m)) * (np.sum(np.square(theta_1[:, 1:])) + np.sum(np.square(theta_2[:, 1:])))

    # Изчисляване на градиентите
    # Задача 3
    d3 = a3.T - y_matrix
    d2 = theta_2[:, 1:].T.dot(d3) + sigmoid_gradient(z2)                    # 25x10 *10x5000 * 25x5000 = 25x5000

    # Задача 3
    delta1 = np.dot(d2, a1)                                                 # 25x5000 * 5000x401 = 25x401
    # Задача 3
    delta2 = d3.dot(a2)                                                     # 10x5000 *5000x26 = 10x26

    theta1_ = np.c_[np.ones((theta_1.shape[0], 1)), theta_1[:, 1:]]
    theta2_ = np.c_[np.ones((theta_2.shape[0], 1)), theta_2[:, 1:]]

    # Задача 3
    theta1_grad = (1/m) * (delta1 + theta1_ * reg)
    # Задача 3
    theta2_grad = (1/m) * (delta2 + theta2_ * reg)

    return J, theta1_grad, theta2_grad


# Параметър за регуляризация = 0
print("Цена без регуляризация: ", nn_cost_function(params, 400, 25, 10, X, y, 0)[0])

# Параметър за регуляризация = 1
print("Цена с регуляризация: ", nn_cost_function(params, 400, 25, 10, X, y, 1)[0])

print("\nСигмоид градиент")
# Задача 4
sigmoid_grad = sigmoid_gradient(-1)
print(sigmoid_grad)
sigmoid_grad = sigmoid_gradient(-0.5)
print(sigmoid_grad)
sigmoid_grad = sigmoid_gradient(0)
print(sigmoid_grad)
sigmoid_grad = sigmoid_gradient(0.5)
print(sigmoid_grad)
sigmoid_grad = sigmoid_gradient(1)
print(sigmoid_grad)

