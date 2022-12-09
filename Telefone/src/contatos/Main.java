package contatos;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Crud crud = new Crud();
		Contato contato1 = new Contato();
		
		
		//crud.select(contato1);
		//crud.create(contato1);
		//crud.update(contato1);
		//crud.delete(1);
		
		
	//Fase de teste (Switch case ainda não fuinciona)
		
	Scanner scan = new Scanner(System.in);
	int acao = 0 ;
		System.out.print("AGENDA TELEFÔNICA \n");
		
		System.out.printf("Escolha qual ação você deseja realizar: \n");
		System.out.printf("1- Visualizar  \n");
		System.out.printf("2- Adicionar  \n");
		System.out.printf("3- Editar  \n");
		System.out.printf("4- Deletar  \n");
		
		switch (acao) {
		
			case 1:
				crud.select(contato1);
				break;
			case 2:
				crud.create(contato1);
				break;
			case 3:
				crud.update(contato1);
				break;
			case 4:
				crud.delete(1);
				break;
			
				}
		scan.close();
				}
		
			
		
	}
