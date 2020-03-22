import pandas as pd
import numpy as np

# load MATLAB files
from scipy.io import loadmat

data = loadmat('data.mat')
data.keys()

y = data['y']
# Add intercept
X = np.c_[np.ones((data['X'].shape[0],1)), data['X']]

print('X:',X.shape, '(with intercept)')
print('y:',y.shape)

weights = loadmat('weights.mat')
weights.keys()

theta1, theta2 = weights['Theta1'], weights['Theta2']
print('theta1 :', theta1.shape)
print('theta2 :', theta2.shape)
params = np.r_[theta1.ravel(), theta2.ravel()]
print('params :', params.shape)

def sigmoid(z):
    return(1 / (1 + np.exp(-z)))

def sigmoidGradient(z):
    return(/* Задача 1 */)
    
def nnCostFunction(nn_params, input_layer_size, hidden_layer_size, num_labels, features, classes, reg):
    
    # When comparing to Octave code note that Python uses zero-indexed arrays.
    # But because Numpy indexing does not include the right side, the code is the same anyway.
    theta1 = nn_params[0:(hidden_layer_size*(input_layer_size+1))].reshape(hidden_layer_size,(input_layer_size+1))
    theta2 = nn_params[(hidden_layer_size*(input_layer_size+1)):].reshape(num_labels,(hidden_layer_size+1))

    m = features.shape[0]
    y_matrix = pd.get_dummies(classes.ravel()).as_matrix() 
    
    # Изчисляване на стойностите във върховете на мрежата (feedforward propagation)
    # за получаване на цената
    a1 = /* Задача 2 - стойности на върховете в слой 1 (входен слой) */ # 5000x401
        
    z2 = /* Задача 2 - стойност z2 във втори слой */ # 25x401 * 401x5000 = 25x5000 
    a2 = np.c_[np.ones((features.shape[0],1)),sigmoid(z2.T)] # 5000x26 
    
    z3 = /* Задача 2 - стойност z3 в слой 3 (изходен слой) */ # 10x26 * 26x5000 = 10x5000 
    a3 = /* Задача 2 - стойност на върха a3 в слой 3 (изходен слой) */ # 10x5000
    
    J = -1*(1/m)*np.sum((np.log(a3.T)*(y_matrix)+np.log(1-a3).T*(1-y_matrix))) + \
        (reg/(2*m))*(np.sum(np.square(theta1[:,1:])) + np.sum(np.square(theta2[:,1:])))

    # Изчисляване на градиентите
    d3 = /* Задача 3 */ # 5000x10
    d2 = theta2[:,1:].T.dot(d3.T)*sigmoidGradient(z2) # 25x10 *10x5000 * 25x5000 = 25x5000
    
    delta1 = /* Задача 3 */ # 25x5000 * 5000x401 = 25x401
    delta2 = /* Задача 3 */ # 10x5000 *5000x26 = 10x26
    
    theta1_ = np.c_[np.ones((theta1.shape[0],1)),theta1[:,1:]]
    theta2_ = np.c_[np.ones((theta2.shape[0],1)),theta2[:,1:]]
    
    theta1_grad = /* Задача 3 */
    theta2_grad = /* Задача 3 */
    
    return(J, theta1_grad, theta2_grad)

# Параметър за регуляризация = 0
print("Цена без регуляризация: ", nnCostFunction(params, 400, 25, 10, X, y, 0)[0])

# Параметър за регуляризация = 1
print("Цена с регуляризация: ", nnCostFunction(params, 400, 25, 10, X, y, 1)[0])

print("\nСигмоид градиент")
[print(sigmoidGradient(z)) /* Задача 4 */]