# Да се построи модел за предсказване на цената на къща с произволна площ чрез линейна регресия.
# С намерената хипотеза да се определи цената на къщи с площ 2*1000 и 4.5*1000 кв. фута.


import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression


data = pd.read_csv('ex1data2.txt', names=['feet2', 'price'])
print(data.head())

x_df = pd.DataFrame(data.feet2)
y_df = pd.DataFrame(data.price)

reg = LinearRegression()
reg.fit(x_df, y_df)

predictions = reg.predict(x_df)

result_constraints = pd.DataFrame([2, 4.5])
result_predictions = reg.predict(result_constraints)

plt.figure(figsize=(16, 8))
plt.plot(x_df, y_df, 'k.')
plt.plot(result_constraints, result_predictions, 'r.', markersize=14)
plt.plot(x_df, predictions, '-')
plt.xlabel('Площ на къщата (feet2)* 1000')
plt.ylabel('Цена ($)* 1000')

plt.show()




