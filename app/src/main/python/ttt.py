import numpy as np
import cv2
from PIL import Image
import base64
import io
import test_fruit

def main(data):
    dd=base64.b64decode(data)
    npData=np.fromstring(dd,np.uint8)
    
    img=cv2.imdecode(npData,cv2.IMREAD_UNCHANGED)
    img_gray=cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    op=checking(img)
    return op
