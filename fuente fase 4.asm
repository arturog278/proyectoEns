; multi-segment executable file template.

data segment
    ; add your data here!
    var1 db 046H
    var2 dw 2345
    var3 db 60 dup(0)
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
CLC 56                  ;ERROR    
INTO BX                 ;ERROR    
INTO                               
PUSHA VAR1              ;ERROR     
PUSHA		                          
PUSHF 090H              ;ERROR      
PUSHF	                              
ET1:                            
IMUL VAR5                          
IMUL 100                ;ERROR    
POP DX                           
POP VAR2,80	            ;ERROR      
ET2:                            
OR DH,10101010b                  
OR VAR5,AL		                  
OR SI,DL                ;ERROR    
ADC DI,0CCH                      
ADC VAR10,CL            ;ERROR     
ADC [BX+060H],20                  
ET3:                            
JGE CONST7              ;ERROR     
JGE ET1       	                  
JMP 0100H               ;ERROR    
JMP ET2	    	                   
ET4:
JNLE DS                 ;ERROR   
JNLE ET3
LOOPE                   ;ERROR
LOOPE ET4  

MOV AX,BX               ;ERROR
NOT VAR4                ;ERROR
AAD                     ;ERROR
ENDS



    