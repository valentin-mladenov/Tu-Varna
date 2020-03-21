# Визуализирайте графиката на функцията y=x2, за стойности на x=[-2;+2]със стъпка 0.01.

import numpy as np
import matplotlib.pyplot as plt


x = [-2, 2.01]
step = 0.01

range_x = np.arange(x[0], x[1], step)
range_y = [x**2 for x in range_x]

plt.plot(range_x, range_y)
plt.show()

