
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

stack segment
    dw 128 dup(0)
ends

