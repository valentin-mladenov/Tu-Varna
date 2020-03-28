# -*- coding: utf-8 -*-
"""
Created on Tue Apr  3 13:57:55 2018

@author: Ivo
"""

import pandas as pd
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt

# Зареждане на MATLAB файлове
from scipy.io import loadmat
from scipy.optimize import minimize

data = loadmat('data.mat')
data.keys()

weights = loadmat('weights.mat')
weights.keys()

y = data['y']
# Добавяне на константа за отклонението (intercept)
X = np.c_[np.ones((data['X'].shape[0], )), data['X']]

print('X: {} (с intercept)'.format(X.shape))
print('y: {}'.format(y.shape))

theta1, theta2 = weights['Theta1'], weights['Theta2']

print('theta1: {}'.format(theta1.shape))
print('theta2: {}'.format(theta2.shape))

sample = np.random.choice(X.shape[0], 20)
plt.imshow(X[sample, 1:].reshape(-1, 20).T)
plt.axis('off')

# plt.show()


def sigmoid(z):
    # Задача 1 - Изчислете сигмоидната функция
    return 1 / (1 + np.exp(-z))


def lr_cost_function_reg(theta_in, reg, x_in, y_in):
    # Задача 2 - Стойност на регуляризираната функция на цената
    # Задача 2 - Брой етикети от обучителните примери
    # Задача 2 - Сойност на сигмоидната функция

    m = y_in.size
    h_in = sigmoid(np.dot(x_in, theta_in))
    regulate = reg * sum(theta_in ** 2) / (2 * m)

    J = -1 * (1 / m) * (np.dot(np.transpose(np.log(h_in)), y_in) + np.dot(np.transpose(np.log(1 - h_in)), 1 - y_in)) + regulate
    
    if np.isnan(J[0]):
        return np.inf

    return J[0]


def lr_gradient_reg(theta_in, reg, x_in, y_in):
    m = y_in.size
    h_in = sigmoid(np.dot(x_in, theta_in.reshape(-1, 1)))

    # Задача 3 - Попълнете първата част от градиента
    grad = (1 / m) * np.transpose(x_in).dot(h_in - y_in) + (reg / m) * np.r_[[[0]], theta_in[1:].reshape(-1, 1)]
        
    return grad.flatten()


def one_vs_all(features, classes, n_labels, reg):
    initial_theta = np.zeros((X.shape[1], 1))               # 401 x 1
    all_theta = np.zeros((n_labels, X.shape[1]))            # 10 x 401

    for c in np.arange(1, n_labels + 1):
        res = minimize(lr_cost_function_reg, initial_theta, args=(reg, features, (classes == c) * 1), method=None,
                       jac=lr_gradient_reg, options={'maxiter': 50})
        all_theta[c - 1] = res.x

    return all_theta


# Задача 4 - Попълнете параметрите при извикване на функцията
theta_all = one_vs_all(X, y, 10, 0.1)


def predict_one_vs_all(all_theta, features):
    # Задача 5 - Изчислете вероятностите за принадлежност към всеки клас. Използвайте сигмоидната функция */
    probabilities = sigmoid(np.dot(features, np.transpose(all_theta)))

    # Добавяне на 1, защото Python използва 0-базирано индексиране на 10 колони (0-9),
    # а 10-те класа са номерирани от 1 до 9.
    return np.argmax(probabilities, axis=1) + 1


# Задача 6 - Попълнете параметрите при извиквания на функцията */
predictions = predict_one_vs_all(theta_all, X)

print('Точност на обучението с логаритмична регресия: {} %'.format(np.mean(predictions == y.ravel())*100))


# Невронна мрежа
def predict(theta_1, theta_2, features):
    # Задача 7 - Изчислете стойността z2 в скрития слой
    z2 = np.dot(theta_1, features.transpose())
    a2 = np.c_[np.ones((data['X'].shape[0], 1)), sigmoid(z2).T]

    # Задача 7 - Изчислете стойността z3 в скрития слой
    z3 = np.dot(a2, theta_2.transpose())
    # Задача 7 - Изчислете стойността a3 в скрития слой
    a3 = sigmoid(z3)

    return np.argmax(a3, axis=1) + 1


# Задача 8 - Попълнете параметрите при извикване на функцията */
predictNN = predict(theta1, theta2, X)
print('Точност на обучението с невронна мрежа: {} %'.format(np.mean(predictNN == y.ravel())*100))
