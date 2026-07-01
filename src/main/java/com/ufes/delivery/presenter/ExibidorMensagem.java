package com.ufes.delivery.presenter;
// Código retirado do material de apoio do professor sobre MVP
// Justificativa:
/* 
Aumentar a testabilidade sem transferir comportamento para a View,
retirando do Presenter a responsabilidade de materializar a
mensagem com JOptionPane, deixando nele apenas a decisão 
semântica sobre qual resposta deve ser apresentada.
o Presenter passa a depender da abstração ExibidorMensagem que
fica responsável por exibir as mensagens, enquanto a classe concreta
dessa abstração ExibidorMensagemJOptionPane utiliza JOptionPane.
Assim, a decisão continua no Presenter, a View permanece passiva
(que é o que queremos) e a parte gráfica da mensagem fica 
isolada em um componente próprio. 
*/
public interface ExibidorMensagem {
    void exibirInformacao(String mensagem, String titulo);
    void exibirErro(String mensagem, String titulo);
}
