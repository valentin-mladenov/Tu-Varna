# -*- coding: utf-8 -*-
"""
Предсказване на цените на къщи
по зададени площ и брой спални
"""

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

data = pd.read_csv('regr_multiple.txt', names=['area', 'bedrooms', 'price'])
print(data.head())

# Разделяме данните площ (area), брой спални (bedrooms) и цена (price) в X и y
# Делим на 10000, за да не се получи overflow в градиентното спускане
X_df = pd.DataFrame({'area': data.area / 10000, 'bedrooms': data.bedrooms})
y_df = pd.DataFrame(data.price / 10000)

## Брой данни за цени на къщи (y)
m = len(y_df)

#Задача 3. Експериментирайте с iterations и alpha
iterations = 1500
alpha = 0.01

## Добавя колона от 1-ци към вектора X
X_df['intercept'] = 1

## Трансформира Numpy масивите за по-лесни операции с матриците
# Създаване на Numpy масив за X
X = np.array(X_df)

# Задача 2. Направете мащабиране на стойностите на променливите площ и брой спални (x1 и x2)

y = np.array(y_df).flatten()
theta = np.array([0, 0, 0])

def cost_function(X, y, theta):
    """
    cost_function(X, y, theta) изчислява функцията на цената за стойности на параметрите theta
    """
    ## брой обучителни примери
    m = len(y) 

    ## Изчислява цената с въведени параметри
    J = np.sum((X.dot(theta)-y)**2)/2/m
    #print(J)
    return J

cost_function(X, y, theta)


def gradient_descent(X, y, theta, alpha, iterations):
    """
    Ф-та gradient_descent изпълнява градиентно спускане за обучаване на параметрите theta
    """
    cost_history = [0] * iterations
    
    for iteration in range(iterations):
        hypothesis = X.dot(theta)
        loss = hypothesis-y
        gradient = X.T.dot(loss)/m
        theta = theta - alpha*gradient
        cost = cost_function(X, y, theta)
        cost_history[iteration] = cost        
    return theta, cost_history

(t, c) = gradient_descent(X,y,theta,alpha, iterations)
print("Минимална цена J = ",min(c))

# Задача 1. Визуализирайте изменението на функцията на цената в итерациите на градиентното спускане.

## Отпечатване на намерените theta
print(t)