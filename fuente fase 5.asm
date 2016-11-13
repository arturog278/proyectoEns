; multi-segment executable file template.

data segment
    ; add your data here!
    var1 db 046H
    var2 dw 2345
    var3 db 6A0 dup(0)  ;ERROR
    var4 db 0347h dup(010h)
    var5 db "tres"
    var6 db 100 dup(' ')
    const7 equ 0
ends

stack segment
    dw 128 dup(0)
ends

code segment
CLC                               
INTO BX                 ;ERROR    
INTO                               
PUSHA		                          
PUSHF	                              
ET1:                            
IMUL VAR5                          
IMUL AH
POP DX                           
POP WORD PTR [BX+5000]
ET2:                            
OR DH,10101010b                  
OR VAR5,AL		                  
OR SI,DX
ADC DI,0CCH                      
ADC VAR6,CL
ADC [BX+060H],20                  
ET3:                            
JGE ET1       	                  
JMP ET2	    	                   
ET4:
JNLE ET3
LOOPE                   ;ERROR
LOOPE ET4  
ENDS



    