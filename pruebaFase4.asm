
data segment
    variableAAAAAAAA db 0         ;ERROR
    variable1 db 10
    variable2 db 3000             ;ERROR
    variable3 dw "HH"             ;ERROR
    variable4 dw 5000H
    variable5 db amigo            ;ERROR
    variable6 db "HOLA"      
    variable7 dw 076879987h       ;ERROR
    variable8 dw 30 dup (67)
    variable9 db 70 dup('A')
    variable10 db 0AAh dup(10)
    variable11 db 5 dup ("bueno") ;ERROR
    const1 equ 6060
    const2 equ 400000             ;ERROR
ends

code segment
CLC
CLC variable1   ;ERROR
INTO 
INTO variable4  ;ERROR
PUSHA AX  ;ERROR
PUSHA
PUSHF dup("lul")  ;ERROR 
label1:
IMUL AX
IMUL variable 1
IMUL variable11  ;ERROR
IMUL SS  ;ERROR
POP DX
POP SS
label2:
POP variable8
POP const2    ;ERROR
POP "lul  ;ERROR
OR AX, DX
OR AX, variable1
OR variable4, DX
OR variable1, 11
OR AX, 53
OR AX, SS  ;ERROR
OR SS, DXX  ;ERROR
OR AX, const1  ;ERROR
label3:
OR 11, DX  ;ERROR
OR CLC, DX  ;ERROR
OR variable4, DX  ;ERROR
ADC AX, DX 
ADC AX, variable1
ADC variable4, DX
ADC variable1, 11
ADC AX, 53
ADC AX, SS  ;ERROR
ADC SS, DXX  ;ERROR
label4:
ADC AX, const1  ;ERROR
ADC 11, DX  ;ERROR
ADC CLC, DX  ;ERROR
JGE label1
JGE lmao ;ERROR
JMP elfly ;ERROR
JMP label2
JNLE oc ;ERROR
JNLE lablel3
LOOPE boosted ;ERROR
LOOPE label4
ends 