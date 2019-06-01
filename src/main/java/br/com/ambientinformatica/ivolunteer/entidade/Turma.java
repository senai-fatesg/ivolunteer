package br.com.ambientinformatica.ivolunteer.entidade;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.ambientinformatica.util.AmbientValidator;

@Entity
public class Turma {

	@Id
	@GeneratedValue(generator = "turma_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "turma_seq", sequenceName = "turma_seq", allocationSize = 1, initialValue = 1)
	private Integer id;

	private String codigo;

	private EnumTurno turno;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@NotNull(message = "Informe o curso.", groups = AmbientValidator.class)
	private Curso curso;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Informe o data de início.", groups = AmbientValidator.class)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Informe a data de término.", groups = AmbientValidator.class)
	private Date dataTermino;
	
	@Temporal(TemporalType.TIME)
	@NotNull(message = "Informe o horário de início.", groups = AmbientValidator.class)
	private Date horarioInicio;

	@Temporal(TemporalType.TIME)
	@NotNull(message = "Informe o horário de término.", groups = AmbientValidator.class)
	private Date horarioFinal;

	@NotNull(message = "Informe a quantidade de vagas.", groups = AmbientValidator.class)
	private Integer quantidadeVagas;

	@Enumerated(EnumType.STRING)
	private EnumSituacao situacao;

	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@NotNull(message = "Informe um professor.", groups = AmbientValidator.class)
	private Colaborador professor;
	
	@PrePersist
	private void cargaInicial() {
		this.codigo = UUID.randomUUID().toString();
		this.situacao = EnumSituacao.AGUARDANDO_INICIO;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public EnumTurno getTurno() {
		return turno;
	}

	public void setTurno(EnumTurno turno) {
		this.turno = turno;
	}

	public Date getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Date horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public Date getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(Date horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public Integer getQuantidadeVagas() {
		return quantidadeVagas;
	}

	public void setQuantidadeVagas(Integer quantidadeVagas) {
		this.quantidadeVagas = quantidadeVagas;
	}

	public EnumSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumSituacao situacao) {
		this.situacao = situacao;
	}

	public Colaborador getProfessor() {
		return professor;
	}

	public void setProfessor(Colaborador professor) {
		this.professor = professor;
	}

}
