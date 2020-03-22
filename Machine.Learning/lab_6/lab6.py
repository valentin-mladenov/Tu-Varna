# -*- coding: utf-8 -*-
"""
Created on Thu Mar  8 14:03:21 2018

@author: Ivo
"""

import numpy as np
import matplotlib.pyplot as plt

from scipy.optimize import minimize

# Функция за зареждане на данни от file в data
# и извеждане на първите шест реда
def loaddata(file, delimeter):
    data = np.loadtxt(file, delimiter=delimeter)
    print('Dimensions: ',data.shape)
    print(data[1:6,:])
    return(data)

# Визуализиране на данни
def plotData(data, label_x, label_y, label_pos, label_neg, axes=None):
    # Получаване на индекси за клас 0 и клас 1
    neg = data[:,2] == 0
    pos = data[:,2] == 1
    
    if axes == None:
        axes = plt.gca()
    axes.scatter(data[pos][:,0], data[pos][:,1], marker='+', c='k', s=60, linewidth=2, label=label_pos)
    axes.scatter(data[neg][:,0], data[neg][:,1], c='y', s=60, label=label_neg)
    axes.set_xlabel(label_x)
    axes.set_ylabel(label_y)
    axes.legend(frameon= True, fancybox = True);
    
data = loaddata('grades.txt', ',')  

#Зареждане на X с входни данни
X = np.c_[np.ones((data.shape[0],1)), data[:,0:2]]
#Зареждане на y с изходни стойности 
y = np.c_[data[:,2]]

#Задача 1 - Визуализиране на данни от data

#Задача 2 - Функция, изчисляваща сигмоидната функция g(z)
def sigmoid(z):
    return # Функцията трябва да връща стойност на g(z) #
    
#Задача 3 - Функцията на цената
def costFunction(theta, X, y):
    # Довършете функцията на цената за параметри theta, X и y
    
    return(J[0])

#Задача 4 - Изчисляване на градиента
def gradient(theta, X, y):
    #Изчислете градиента в масив grad

    return(grad.flatten())

#Начални стойности на параметрите theta
initial_theta = np.zeros(X.shape[1])

#Изчисляване на цената за началните стойности
cost = costFunction(initial_theta, X, y)

#Изчисляване на градиента за началните стойности
grad = gradient(initial_theta, X, y)

print('Cost: \n', cost)
print('Grad: \n', grad)

#Намиране на минимум на цената
res = minimize(costFunction, initial_theta, args=(X,y), method=None, jac=gradient, options={'maxiter':400})
res

#Функция за предсказване на резултат от неизвестни данни
def predict(theta, X, threshold=0.5):
    p = sigmoid(X.dot(theta.T)) >= threshold
    return(p.astype('int'))

#Задача 5 - Предсказване на вероятност за приемане на студент с оценка 1 - 45 точки, оценка 2 - 87 точки
#Изчислете каква е вероятността

#Изчисляване на точност на обучението
p = predict(res.x, X) 
print('Train accuracy {}%'.format(100*sum(p == y.ravel())/p.size))

#Построяване на граница (Decision boundary) между положителните и негативните класовев хипотезата
plt.scatter(45, 85, s=60, c='r', marker='v', label='(45, 85)')
plotData(data, 'Оценка 1', 'Оценка 2', 'Приет', 'Не е приет')
x1_min, x1_max = X[:,1].min(), X[:,1].max(),
x2_min, x2_max = X[:,2].min(), X[:,2].max(),
xx1, xx2 = np.meshgrid(np.linspace(x1_min, x1_max), np.linspace(x2_min, x2_max))
h = sigmoid(np.c_[np.ones((xx1.ravel().shape[0],1)), xx1.ravel(), xx2.ravel()].dot(res.x))
h = h.reshape(xx1.shape)
plt.contour(xx1, xx2, h, [0.5], linewidths=1, colors='b'); 
    


    