# Даден е символен низ "I  love  chocolate  very  much". Да се запише всяка дума от низа на отделен ред в текстов файл.


data = 'I  love  chocolate  very  much'

with open("write_string_result.txt", "w") as file:
    file.write('\n'.join(data.split('  ')))

    file.close()
