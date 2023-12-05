/* 
 * Gabriel de Almeida Silva | 172311246
 * Guilherme Eduardo | 942311706 
 * Thiago Cesar Magalhães | 172312614
 * Vinícius Stencel Canto Silva | 172315471 
 */

package com.github.thiagomagh.antivirus;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String diretorioInicial = "D:\\Downloads\\Arquivos atividades";
		String[] extensoes = { "execucao-aula-teste.exe", "execucao-aula.exe", "script-aula-teste.bat",
				"script-aula.bat" };

		buscarArquivosPorExtensao(diretorioInicial, extensoes);
	}

	public static void buscarArquivosPorExtensao(String diretorio, String[] extensoes) {
		Queue<File> fila = new LinkedList<>();
		fila.add(new File(diretorio));
		int index = 0;

		while (index < extensoes.length) {
			File dir = fila.poll();
			File[] arquivos = dir.listFiles();

			if (arquivos != null) {
				for (File arquivo : arquivos) {
					if (arquivo.isDirectory()) {
						fila.add(arquivo);
					} else {
						for (String extensao : extensoes) {
							if (arquivo.getName().endsWith(extensao)) {
								System.out.println("Arquivo encontrado: " + arquivo.getAbsolutePath());

								// Adiciona a opção de exclusão
								if (confirmarExclusao()) {
									excluirArquivo(arquivo);
								}
								
								index++;
								break;
							}
						}
					}
				}
			}
		}
		
		System.out.println("Busca de arquivos concluída.");
	}

	public static boolean confirmarExclusao() {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Deseja excluir o arquivo? (S/N): ");
		String resposta = scanner.nextLine().trim().toUpperCase();
		return resposta.equals("S");
	}

	public static void excluirArquivo(File arquivo) {
		if (arquivo.delete()) {
			System.out.println("Arquivo excluído com sucesso.");
		} else {
			System.out.println("Falha ao excluir o arquivo.");
		}
	}
}
