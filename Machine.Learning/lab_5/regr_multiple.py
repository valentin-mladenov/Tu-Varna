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
iterations = 300
alpha = 0.3

## Добавя колона от 1-ци към вектора X
X_df['intercept'] = 1

## Трансформира Numpy масивите за по-лесни операции с матриците
# Създаване на Numpy масив за X
X = np.array(X_df)

# Задача 2. Направете мащабиране на стойностите на променливите площ и брой спални (x1 и x2)
# X_df.area = (X_df.area - X_df.area.mean()) / X_df.area.std()
# X_df.bedrooms = (X_df.bedrooms - X_df.bedrooms.mean()) / X_df.bedrooms.std()
# X = np.array(X_df)
# print(X_df)


# Задача 4. Направете мащабирането на променливите универсално(не само за x1 и x2).
X_df = (X_df - X_df.mean()) / X_df.std()
X_df['intercept'] = 1
X = np.array(X_df)

y = np.array(y_df).flatten()
theta = np.array([0, 0, 0])


def cost_function(X, y, theta):
    """
    cost_function(X, y, theta) изчислява функцията на цената за стойности на параметрите theta
    """
    m = len(y) 

    J = np.sum((X.dot(theta)-y)**2)/2/m
    return J


def gradient_descent(X, y, theta_in, alpha_in, iterations_in):
    """
    Ф-та gradient_descent изпълнява градиентно спускане за обучаване на параметрите theta
    """
    cost_history = [0] * iterations_in

    for iteration in range(iterations_in):
        hypothesis = X.dot(theta_in)
        loss = hypothesis - y
        gradient = X.T.dot(loss)/m
        theta_in = theta_in - alpha_in * gradient
        cost = cost_function(X, y, theta_in)
        cost_history[iteration] = cost
    return theta_in, cost_history


(t, c) = gradient_descent(X, y, theta, alpha, iterations)
print("Минимална цена J = ", min(c))

# Задача 1. Визуализирайте изменението на функцията на цената в итерациите на градиентното спускане.

## Отпечатване на намерените theta
print(t)

best_fit_x = np.linspace(0, 25, 20)
best_fit_y = [t[1] + t[0]*xx for xx in best_fit_x]

plt.figure(figsize=(16, 8))
plt.axis([0, 5, 0, 80])
plt.plot(X_df.area, y_df, 'r.')
plt.plot(X_df.bedrooms, y_df, 'g.')
plt.plot(best_fit_x, best_fit_y, '-')
plt.xlabel('Площ на къщата (feet2)* 1000')
plt.ylabel('Цена ($)* 1000')

plt.show()
