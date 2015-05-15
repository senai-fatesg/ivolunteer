package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;

public class Funcionario {

	private String pis;

	private String carteiraDeTrabalho;

	private String serieCarteiraDeTrabalho;

	private String reservista;

	private Date emissaoReservista;

	private String cNH;

	private Date emissaoCNH;

	private Date validadeCNH;

	private String nomePai;

	private String nomeMae;

	private String email;

	private String titulo;

	private String zona;

	private String secao;

	private boolean exameAdmissional;

	private Date dataExameAdmissional;

	private Date dataAdmissao;

	private Date inicioExperiencia;

	private Date terminoExperiencia;

	private String banco;

	private String agencia;

	private String conta;

	private Date dataDemissao;

	private Double valorAcerto;

	private String observacao;

	private String historico;

	private EnumFuncao funcao;

	private Dependente[] dependente;

	private Frequencia frequencia;

	private GradeHorario gradeHorario;

	private AtividadeDiaria atividadeDiaria;

	private Turma turma;

}
