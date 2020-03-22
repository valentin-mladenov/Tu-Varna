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
X = np.c_[np.ones((data['X'].shape[0],1)), data['X']]

print('X: {} (с intercept)'.format(X.shape))
print('y: {}'.format(y.shape))

theta1, theta2 = weights['Theta1'], weights['Theta2']

print('theta1: {}'.format(theta1.shape))
print('theta2: {}'.format(theta2.shape))

sample = np.random.choice(X.shape[0], 20)
plt.imshow(X[sample,1:].reshape(-1,20).T)
plt.axis('off');

def sigmoid(z):
    return /* Задача 1 - Изчислете сигмоидната функция */
    
def lrcostFunctionReg(theta, reg, X, y):
    m = /* Задача 2 - Брой етикети от обучителните примери */
    h = /* Задача 2 - Сойност на сигмоидната функция */
    
    J = /* Задача 2 - Стойност на регуляризираната функция на цената */
    
    if np.isnan(J[0]):
        return(np.inf)
    return(J[0])

def lrgradientReg(theta, reg, X,y):
    m = y.size
    h = sigmoid(X.dot(theta.reshape(-1,1)))
      
    grad = /* Задача 3 - Попълнете първата част от градиента */ + (reg/m)*np.r_[[[0]],theta[1:].reshape(-1,1)]
        
    return(grad.flatten())
    
def oneVsAll(features, classes, n_labels, reg):
    initial_theta = np.zeros((X.shape[1],1))  # 401x1
    all_theta = np.zeros((n_labels, X.shape[1])) #10x401

    for c in np.arange(1, n_labels+1):
        res = minimize(lrcostFunctionReg, initial_theta, args=(reg, features, (classes == c)*1), method=None,
                       jac=lrgradientReg, options={'maxiter':50})
        all_theta[c-1] = res.x
    return(all_theta)
    
theta = oneVsAll(/* Задача 4 - Попълнете параметрите при извикване на функцията */)

def predictOneVsAll(all_theta, features):
    probs = /* Задача 5 - Изчислете вероятностите за принадлежност към всеки клас. Използвайте сигмоидната функция */
        
    # Добавяне на 1, защото Python използва 0-базирано индексиране на 10 колони (0-9),
    # а 10-те класа са номерирани от 1 до 9.
    return(np.argmax(probs, axis=1)+1)
    
pred = predictOneVsAll(/* Задача 6 - Попълнете параметрите при извиквания на функцията */)
print('Точност на обучението с логаритмична регресия: {} %'.format(np.mean(pred == y.ravel())*100))

# Невронна мрежа
def predict(theta_1, theta_2, features):
    z2 = /* Задача 7 - Изчислете стойността z2 в скрития слой */
    a2 = np.c_[np.ones((data['X'].shape[0],1)), sigmoid(z2).T]
    
    z3 = /* Задача 7 - Изчислете стойността z3 в изходния слой  */
    a3 = /* Задача 7 - Изчислете стойността на върха a3 в изходния слой  */
        
    return(np.argmax(a3, axis=1)+1) 
    
pred = predict(/* Задача 8 - Попълнете параметрите при извикване на функцията */)
print('Точност на обучението с невронна мрежа: {} %'.format(np.mean(pred == y.ravel())*100))
