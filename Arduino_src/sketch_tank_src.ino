#define MOTOR_PIN_L1  3
#define MOTOR_PIN_L2  2
#define MOTOR_PWM_L 6

#define MOTOR_PIN_R1  8
#define MOTOR_PIN_R2  7
#define MOTOR_PWM_R 10

#define ON  1
#define OFF  0

byte bufByte[4] = {0, 0, 0, 0};

void setup() {

  Serial.begin(9600);
  
  pinMode(MOTOR_PIN_L1, OUTPUT);
  pinMode(MOTOR_PIN_L2, OUTPUT);
  pinMode(MOTOR_PWM_L, OUTPUT);
  
  pinMode(MOTOR_PIN_R1, OUTPUT);
  pinMode(MOTOR_PIN_R2, OUTPUT);
  pinMode(MOTOR_PWM_R, OUTPUT);
  
  //digitalWrite(MOTOR_PIN_L1,1);
 // digitalWrite(MOTOR_PIN_L2,0);
  digitalWrite(MOTOR_PWM_L,255);
       
 // digitalWrite(MOTOR_PIN_R1,1);
 // digitalWrite(MOTOR_PIN_R2,0);
  digitalWrite(MOTOR_PWM_R,255);
  
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }
}

void loop() {

  if (Serial.available() > 0) {
    Serial.readBytes(bufByte, 4);
    
    // 메시지 받은 주체가 시리얼일 경우만 동작
    if(checkTarget(bufByte[0])) 
    {
      // 모터
      if(bufByte[1] == 1) 
      {
        if((bufByte[2] & 15) == 3) //두 모터모두 동작
        {
          if((bufByte[3] & 15) == 0) // 중지
          {
             digitalWrite(MOTOR_PIN_L1,OFF);
             digitalWrite(MOTOR_PIN_L2,OFF);
             digitalWrite(MOTOR_PIN_R1,OFF);
             digitalWrite(MOTOR_PIN_R2,OFF);
          }
          if((bufByte[3] & 15) == 1) // 앞으로
          {
             digitalWrite(MOTOR_PIN_L1,OFF);
             digitalWrite(MOTOR_PIN_L2,ON);
             digitalWrite(MOTOR_PIN_R1,OFF);
             digitalWrite(MOTOR_PIN_R2,ON);
             //Serial.write(bufByte, 4);
          }
          if((bufByte[3] & 15) == 2) // 뒤로
          {
            
             digitalWrite(MOTOR_PIN_L1,ON);
             digitalWrite(MOTOR_PIN_L2,OFF);
             digitalWrite(MOTOR_PIN_R1,ON);
             digitalWrite(MOTOR_PIN_R2,OFF);
          }
       }
        else if((bufByte[2] & 15) == 1) // 왼쪽모터만 움직여 오른쪽으로 이동
        {
            if((bufByte[3] & 15) == 0) // 중지
            {
               digitalWrite(MOTOR_PIN_L1,OFF);
               digitalWrite(MOTOR_PIN_L2,OFF);
               digitalWrite(MOTOR_PIN_R1,OFF);
               digitalWrite(MOTOR_PIN_R2,OFF);
            }
            if((bufByte[3] & 15) == 1) // 앞으로
            {
               digitalWrite(MOTOR_PIN_L1,OFF);
               digitalWrite(MOTOR_PIN_L2,ON);
               digitalWrite(MOTOR_PIN_R1,OFF);
               digitalWrite(MOTOR_PIN_R2,OFF);
            }
            if((bufByte[3] & 15) == 2) // 뒤로
            {
              
               digitalWrite(MOTOR_PIN_L1,ON);
               digitalWrite(MOTOR_PIN_L2,OFF);
               digitalWrite(MOTOR_PIN_R1,OFF);
               digitalWrite(MOTOR_PIN_R2,OFF);
            }
        }
        else if((bufByte[2] & 15) == 2) // 오른쪽모터만 움직여 왼쪽으로 이동
        {
             if((bufByte[3] & 15) == 0) // 중지
              {
                digitalWrite(MOTOR_PIN_L1,OFF);
                digitalWrite(MOTOR_PIN_L2,OFF);
                digitalWrite(MOTOR_PIN_R1,OFF);
                digitalWrite(MOTOR_PIN_R2,OFF);
              }
              if((bufByte[3] & 15) == 1) // 앞으로
              {
                 digitalWrite(MOTOR_PIN_L1,OFF);
                 digitalWrite(MOTOR_PIN_L2,OFF);
                 digitalWrite(MOTOR_PIN_R1,OFF);
                 digitalWrite(MOTOR_PIN_R2,ON);
              }
              if((bufByte[3] & 15) == 2) // 뒤로
              {
                
                 digitalWrite(MOTOR_PIN_L1,OFF);
                 digitalWrite(MOTOR_PIN_L2,OFF);
                 digitalWrite(MOTOR_PIN_R1,ON);
                 digitalWrite(MOTOR_PIN_R2,OFF);
              }
        }
        
      }
      // LED
      if (bufByte[1] == 2)
      {
        ;
      }
    }
  }
  delay(500);
}

// 메시지 받을 주체를 확인 
bool checkTarget(byte data)
{
  byte tmp = data & 15;
  if(tmp == 3)
    return true;
  else
    return false;
}

