# -*- coding: utf-8 -*-
"""
Created on Thu Mar  8 14:03:21 2018

@author: Ivo
"""

import numpy as np
import matplotlib.pyplot as plt

from scipy.optimize import minimize

from sklearn.preprocessing import PolynomialFeatures


# Функция за зареждане на данни от file в data
# и извеждане на първите шест реда
def load_data(file, delimiter):
    data = np.loadtxt(file, delimiter=delimiter)
    print('Dimensions: ', data.shape)
    print(data[1:6, :])
    return data


# Визуализиране на данни
def plot_data(data, label_x, label_y, label_pos, label_neg, axes_in=None):
    # Получаване на индекси за клас 0 и клас 1
    neg = data[:, 2] == 0
    pos = data[:, 2] == 1
    
    if axes_in is None:
        axes_in = plt.gca()

    axes_in.scatter(data[pos][:, 0], data[pos][:, 1], marker='+', c='k', s=60, linewidth=2, label=label_pos)
    axes_in.scatter(data[neg][:, 0], data[neg][:, 1], c='y', s=60, label=label_neg)
    axes_in.set_xlabel(label_x)
    axes_in.set_ylabel(label_y)
    axes_in.legend(frameon=True, fancybox=True)


# Функция изчисляваща сигмоидната функция g(z)
def sigmoid(z):
    calc = 1 + np.exp(-z)
    return 1 / calc


# Функция за предсказване
def predict(theta, x_in, threshold=0.5):
    p = sigmoid(x_in.dot(theta.T)) >= threshold
    return p.astype('int')


data2 = load_data('chip_data.txt', ',')

y = np.c_[data2[:, 2]]
X = data2[:, 0:2]

plot_data(data2, 'Microchip тест 1', 'Microchip тест 2', 'y = 1', 'y = 0')

# Функция, която добавя колона с 1 в матрицата
poly = PolynomialFeatures(6)
XX = poly.fit_transform(data2[:, 0:2])

print(XX.shape)


def cost_function_reg(theta, reg, *args):
    m = y.size
    h_in = sigmoid(XX.dot(theta))

    regulate = reg * sum(theta**2) / (2 * m)

    J = -1 * (1 / m) * (np.log(h_in).T.dot(y) + np.log(1 - h_in).T.dot(1 - y)) + regulate
    
    if np.isnan(J[0]):
        return np.inf
    return J[0]


def gradient_reg(theta, reg, *args):
    m = y.size
    h_in = sigmoid(XX.dot(theta.reshape(-1, 1)))
      
    grad = (1 / m) * XX.T.dot(h_in - y) + (reg/m) * np.r_[[[0]], theta[1:].reshape(-1, 1)]
        
    return grad.flatten()


initial_theta = np.zeros(XX.shape[1])
cost_function_reg(initial_theta, 1, XX, y)

fig, axes = plt.subplots(1, 3, sharey=True, figsize=(17, 5))

# Decision boundaries
# Lambda = 0 : Без регуляризация
# Lambda = 1 : Регуляризация
# Lambda = 100 : Твърде голяма регуляризация

for i, C in enumerate([0, 1, 100]):
    # Оптимизация на costFunctionReg
    res2 = minimize(cost_function_reg, initial_theta, args=(C, XX, y), method=None, jac=gradient_reg, options={'maxiter':3000})

    # Точност на обучението
    accuracy = 100 * sum(predict(res2.x, XX) == y.ravel())/y.size

    # Scatter plot of X,y
    plot_data(data2, 'Microchip тест 1', 'Microchip тест 2', 'y = 1', 'y = 0', axes.flatten()[i])

    # Визуализира границата между положителни и отрицателни класове (decision boundary)
    x1_min, x1_max = X[:, 0].min(), X[:, 0].max(),
    x2_min, x2_max = X[:, 1].min(), X[:, 1].max(),
    xx1, xx2 = np.meshgrid(np.linspace(x1_min, x1_max), np.linspace(x2_min, x2_max))
    h = sigmoid(poly.fit_transform(np.c_[xx1.ravel(), xx2.ravel()]).dot(res2.x))
    h = h.reshape(xx1.shape)
    axes.flatten()[i].contour(xx1, xx2, h, [0.5], linewidths=1, colors='g')
    axes.flatten()[i].set_title('Точност на обучение {}% при Lambda = {}'.format(np.round(accuracy, decimals=2), C))

plt.show()


