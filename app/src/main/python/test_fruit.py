from keras.models import load_model
from keras.preprocessing import image
import os
import numpy as np
import matplotlib.pyplot as plt
#no_warn()
#model = load_model('tomato.h5')
#no_warn()
#model.summary()
class Fruit:
    
    def __init__(self, img_dir = ''):
        self.img_dir = ''
        self.cnt = 0
        self.batch_holder = None
        self.model = load_model('tomato.h5')
        self.Label_dict = labels =  {'Tomato 1': 0,
             'Tomato 2': 1,
             'Tomato 3': 2,
             'Tomato 4': 3,
             'Tomato Cherry Red': 4,
             'Tomato Heart': 5,
             'Tomato Maroon': 6,
             'Tomato Yellow': 7,                        
             'Tomato not Ripened': 8 }
        
        self.label = list(self.Label_dict.keys())
    def read_images(self,imgdata):
        self.cnt = len(os.listdir(self.img_dir))
        self.batch_holder = np.zeros((self.cnt, 100, 100, 3))
        for i,img in enumerate(os.listdir(self.img_dir)):
            img = image.load_img(os.path.join(self.img_dir,img), target_size=(100, 100))
            self.batch_holder[i, :] = img
        return self.batch_holder
    
    def predictdata(self):
        #fig = plt.figure(figsize=(20, 20))
        label=""
        for i,img in enumerate(self.batch_holder):
            #fig.add_subplot(5, 5, i+1)
            result=self.model.predict(self.batch_holder)
            result_classes = result.argmax(axis=-1)
            label=self.label[result_classes[i]]
            
        return label
def checking(imgdata):
    obj = Fruit('new')
    #obj.read_images()
    obj.batch_holder = np.zeros((1, 100, 100, 3))
    img=image.load_img(os.path.join(imgdata), target_size=(100, 100))
    obj.batch_holder[0, :] = img
    return obj.predictdata()


