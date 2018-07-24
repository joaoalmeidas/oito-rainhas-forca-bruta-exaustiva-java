import java.util.Scanner;

public class OitoRainhas {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int[][] tabuleiro = new int [8][8];
		
		int rainha = 0, tentativa = 1;;
		boolean ocupado, oitoRainhas = false;
		
		System.out.println("Desafio das Oito Rainha\n"
				+ "Esse programa busca resolver o desafio das oito rainhas usando a abordagem de força bruta exaustiva,\n"
				+ "testando todas as possibilidades, até encontrar uma combinação em que todas as rainhas estejam posicionadas no tabuleiro\n"
				+ "e em posições onde não é possivel que se ataquem entre si.\n"
				+ "Pressione Enter para iniciar o programa.");
		
		input.nextLine();
		
		//define a posição em que primeira rainha sera posicionada.
		for(int primeiraLinha = 0; primeiraLinha < tabuleiro.length && oitoRainhas == false; primeiraLinha++){
			for(int primeiraColuna = 0; primeiraColuna < tabuleiro[0].length && oitoRainhas == false; primeiraColuna++){
				
				System.out.printf("Tentativa %d\n", tentativa);
				tentativa++;
				
				inicializaTabuleiro(tabuleiro);
				
				//bloqueia as posições que a primeira rainha posicionada esta atacando.
				for(int x = 0; x < tabuleiro.length; x++){
					tabuleiro[primeiraLinha][x] = 2;
				}
				for(int x = 0; x < tabuleiro[0].length; x++){
					tabuleiro[x][primeiraColuna] = 2;
				}
				for(int x = 0; x < tabuleiro.length; x++){
					
					if(primeiraLinha - x >= 0 && primeiraColuna - x >= 0){
						tabuleiro[primeiraLinha - x][primeiraColuna - x] = 2;
					}
					if(primeiraLinha - x >= 0 && primeiraColuna + x < 8){
						tabuleiro[primeiraLinha - x][primeiraColuna + x] = 2;
					}
					if(primeiraLinha + x < 8 && primeiraColuna - x >= 0){
						tabuleiro[primeiraLinha + x][primeiraColuna - x] = 2;
					}
					if(primeiraLinha + x < 8 && primeiraColuna + x < 8){
						tabuleiro[primeiraLinha + x][primeiraColuna + x] = 2;
					}
				}
				
				//posiciona a primeira rainha
				tabuleiro[primeiraLinha][primeiraColuna] = 1;
				
				//posiciona o restante das rainhas, sempre posicionando a rainha na primeira casa encontrada que não esteja ocupada ou atacada por uma rainha.
				for(int linha = 0; linha < tabuleiro.length; linha++){
					for(int coluna = 0; coluna < tabuleiro[0].length; coluna++){
						
						//bloqueia as casas que a rainha está atacando
						if(tabuleiro[linha][coluna] == 0){
						
							for(int i = 0; i < tabuleiro.length; i++){
								tabuleiro[linha][i] = 2;
							}
							for(int i = 0; i < tabuleiro[0].length; i++){
								tabuleiro[i][coluna] = 2;
							}
							for(int i = 0; i < tabuleiro.length; i++){
								
								if(linha - i >= 0 && coluna - i >= 0){
									tabuleiro[linha - i][coluna - i] = 2;
								}
								if(linha - i >= 0 && coluna + i < 8){
									tabuleiro[linha - i][coluna + i] = 2;
								}
								if(linha + i < 8 && coluna - i >= 0){
									tabuleiro[linha + i][coluna - i] = 2;
								}
								if(linha + i < 8 && coluna + i < 8){
									tabuleiro[linha + i][coluna + i] = 2;
								}
							}
							//posiciona a rainha
							tabuleiro[linha][coluna] = 1;
						}	
					}	
				}
				
				rainha = 0;
				//verifica se há oito rainhas no tabuleiro
				for(int i = 0; i < tabuleiro.length; i++){
					for(int j = 0; j < tabuleiro[0].length; j++){
						if(tabuleiro[i][j] == 1){
							rainha++;
						}
					}
				}
				
				if(rainha == 8){
					oitoRainhas = true;
				}
				
				//exibe a quantidade de rainhas que a tentativa conseguiu posicionar no tabuleiro e o tabuleiro em si.
				System.out.printf("%d rainhas.\n", rainha);	
				exibeTabuleiro(tabuleiro);
			}
		}
	}
	
	public static void inicializaTabuleiro(int[][] tabuleiro){
		for(int i = 0; i < tabuleiro.length; i++){
			for(int j = 0; j < tabuleiro[0].length; j++){
				tabuleiro[i][j] = 0;
			}
		}
	}
	
	public static void exibeTabuleiro(int [][] tabuleiro){
		for(int i = 0; i < tabuleiro.length; i++){
			for(int j = 0; j < tabuleiro[0].length; j++){
				if(tabuleiro[i][j] == 0){
					System.out.printf("  |");
				}else if(tabuleiro[i][j] == 1){
					System.out.printf(" R|");
				}else if(tabuleiro[i][j] == 2){
					System.out.printf(" *|");				}
			}
			System.out.println();
		}
		System.out.println();
	}

}
