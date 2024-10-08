import cv2
import numpy as np
def enhancement(img):
    # 작성 할 부분 (GT 이미지 사용시 0점)
    # 1. 화이트 밸런스
    result = cv2.cvtColor(img, cv2.COLOR_BGR2YCrCb)
    y_channel, cr_channel, cb_channel = cv2.split(result)

    # Y 채널의 히스토그램 평활화
    y_channel = cv2.equalizeHist(y_channel)

    # 색상 채널을 다시 합침
    result = cv2.merge((y_channel, cr_channel, cb_channel))
    result = cv2.cvtColor(result, cv2.COLOR_YCrCb2BGR)

    # 2. 대비 향상 (CLAHE)
    lab = cv2.cvtColor(result, cv2.COLOR_BGR2LAB)
    l_channel, a_channel, b_channel = cv2.split(lab)

    # CLAHE를 사용하여 L 채널을 평활화
    clahe = cv2.createCLAHE(clipLimit=0.1, tileGridSize=(13, 13))  # clipLimit 조정
    cl = clahe.apply(l_channel)

    # 다시 합침
    lab = cv2.merge((cl, a_channel, b_channel))
    result = cv2.cvtColor(lab, cv2.COLOR_LAB2BGR)

    
    # 3. 색상 보정
    result = cv2.cvtColor(result, cv2.COLOR_BGR2HSV)
    h_channel, s_channel, v_channel = cv2.split(result)

    # V 채널의 히스토그램 평활화
    v_channel = cv2.equalizeHist(v_channel)
    result = cv2.merge((h_channel, s_channel, v_channel))
    result = cv2.cvtColor(result, cv2.COLOR_HSV2BGR)

    

    # 다른 채널 강조 (예: 파란색과 빨간색을 조금 강조)
    b, g, r = cv2.split(result)

    # 초록색 채널 줄이기
    g = (g * 0.65).astype(np.uint8)
    b = (b * 0.6).astype(np.uint8)
    r = (r * 1).astype(np.uint8)

    # 채널 재결합
    result = cv2.merge((b, g, r))

    result = cv2.cvtColor(result, cv2.COLOR_BGR2HLS)

    # 채도 증가
    result[..., 2] = np.clip(result[..., 2] * 0.45, 0, 255)

    # 다시 RGB로 변환
    result = cv2.cvtColor(result, cv2.COLOR_HLS2BGR)
    
    
     # 5. 색상 강도 조정 (Gamma Correction)
    gamma = 1.55  # 감마 조정값
    inv_gamma = 1.0 / gamma
    table = np.array([(i / 255.0) ** inv_gamma * 255 for i in range(256)]).astype("uint8")
    result = cv2.LUT(result, table)

    g = 0.8
    result = result.copy()
    result = result.astype(np.float32)
    result = (result/ 255) ** g * 255
    result = result.astype(np.uint8)
    
    # 4. 노이즈 감소 (가우시안 블러 사용)
    result = cv2.GaussianBlur(result, (3, 3), 0.5)  # 커널 크기 조정
    result = cv2.addWeighted(result, 1.5, result, -0.5, 1)

    return result


# 0.8628
