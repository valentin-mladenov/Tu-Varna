# Начертайте графиката на функцията на цената. Наблюдавайте как се променя стойността на цената с увеличаване на итерациите в градиентното спускане.


import inline
import matplotlib
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# %matplotlib inline

data = pd.read_csv('ex1data1.txt', names=['population', 'profit'])
print(data.head())

# Разделяме данните население (population) и доходност (profit) в x и y
x_df = pd.DataFrame(data.population)
y_df = pd.DataFrame(data.profit)

m = len(y_df)

iterations = 15000
alpha = 0.01

x_df['intercept'] = 1

x = np.array(x_df)
y = np.array(y_df).flatten()
theta = np.array([0, 0])


def cost_function(x_in, y_in, theta_in):
    y_len = len(y_in)
    J = np.sum((x_in.dot(theta_in) - y_in) ** 2) / 2 / y_len

    return J


def gradient_descent(x_in, y_in, theta_inner, alpha_in, iterations_in):
    cost_history = [0] * iterations_in
    for iteration in range(iterations_in):
        hypothesis = x_in.dot(theta_inner)
        loss = hypothesis - y_in
        gradient = x_in.T.dot(loss) / m
        theta_inner = theta_inner - alpha_in * gradient
        cost = cost_function(x_in, y_in, theta_inner)
        cost_history[iteration] = cost

    return theta_inner, cost_history


(t, c) = gradient_descent(x, y, theta, alpha, iterations)

best_fit_x = np.linspace(0, 25, 20)
# best_fit_y = [t[1] + t[0]*xx for xx in best_fit_x]
#
# plt.figure(figsize=(10, 6))
# plt.plot(x_df.population, y_df, '.')
# plt.plot(best_fit_x, best_fit_y, '-')
# plt.axis([0, 25, -5, 25])
# plt.xlabel('Population of City in 10,000s')
# plt.ylabel('Profit in $10,000s')
# plt.title('Profit vs. Population with Linear Regression Line')
#
# plt.show()

cost_y = [c[1] + c[0]*xx for xx in best_fit_x]

plt.figure(figsize=(10, 6))
plt.plot(best_fit_x, cost_y, '-')
plt.axis([1, 30, 0, 200])
plt.xlabel('Population of City in 10,000s')
plt.ylabel('Profit in $10,000s')
plt.title('Profit vs. Population with Linear Regression Line')

plt.show()

# Историята на цената не се променя спрямо итерациите.
