import java.util.*;

class FilaController {
    private Queue<String> filaPedidos = new LinkedList<>();
    public void adicionarPedido(String p){ filaPedidos.add(p); }
    public String atenderPedido(){ return filaPedidos.poll(); }
    public void listarFila(){ System.out.println("Fila: "+filaPedidos); }
    public boolean filaVazia(){ return filaPedidos.isEmpty(); }
}
