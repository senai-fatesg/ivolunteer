package br.com.ambientinformatica.ivolunteer.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.IndexColumn;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Pessoa {

	@Id
	@GeneratedValue(generator = "pessoa_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "pessoa_seq", sequenceName = "pessoa_seq", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	private String nomePessoa;
	private String rg;
	private String orgaoExpeditor;
	private String naturalidade;
	private String cpf;
	private String nascionalidade;
	private String descricao;
	private String profissao;
	private String indicacao;
	private String certidaoNascimento;
	private String informacoesSobreIntituicao;
	
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;
	
	@Enumerated(EnumType.STRING)
	private EnumEscolaridade escolaridade;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoPessoa tipoPessoa;
	
	@Enumerated(EnumType.STRING)
	private EnumEstadoCivil estadoCivil;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoCasa tipoMoradia;

	private String necessidadesEspeciais;

	@Enumerated(EnumType.STRING)
	private EnumFiliacao filiacao;
	@Enumerated(EnumType.STRING)
	
	private EnumSexo enumSexo;
	@Enumerated(EnumType.STRING)

	private EnumEscolaridade enumEscolaridade;
	@Enumerated(EnumType.STRING)
	private EnumTipoTelefone enumTipoPessoa;
	
	@Enumerated(EnumType.STRING)
	private EnumPrioridade enumPrioridade;
	
	public EnumPrioridade getEnumPrioridade() {
		return enumPrioridade;
	}

	public void setEnumPrioridade(EnumPrioridade enumPrioridade) {
		this.enumPrioridade = enumPrioridade;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExpedicao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	//data de vencimento criada devido o documento de cadastro de candidato ter validade de 6 meses
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;
	
	private BigDecimal valorBeneficio = BigDecimal.ZERO;
	
	private BigDecimal rendaOutra = BigDecimal.ZERO;
	private BigDecimal rendaPai = BigDecimal.ZERO;
	private BigDecimal rendaMae = BigDecimal.ZERO;
	private BigDecimal rendaResponsavel = BigDecimal.ZERO;
	private BigDecimal totalRenda = BigDecimal.ZERO;
	
	private BigDecimal valorAluguel = BigDecimal.ZERO;
	private BigDecimal valorInicial = BigDecimal.ZERO;
	private BigDecimal valorFinal = BigDecimal.ZERO;
	private Integer numeroDePessoasMoradia;
	private Integer numeroFilhosMatriculados;
	
	private Boolean paisVivemJuntos;
	private Boolean requisitouOutraVaga;
	private Boolean recebeBeneficio = false;
	private Boolean requisitouVagaParaOutraCriancao;

	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id")
	@IndexColumn(name = "id")
   private List<Endereco> listaEndereco = new ArrayList<Endereco>();
	
	@OneToMany
	private List<Pessoa> listaPessoaRelacionada = new ArrayList<Pessoa>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id")
	@IndexColumn(name = "id")
	private List<Telefone> listaTelefone = new ArrayList<Telefone>();
	
	//construtor da classe
	public Pessoa(){
	}

	public BigDecimal getTotalRenda() {
		return null;
	}

	public Integer getIrmaosMatriculados() {
		return 0;
	}

	public Integer getIdade() {
		
		return 0;
	}

	public void addTelefone(Telefone telefone){
		if(!this.listaTelefone.contains(telefone)){
			this.listaTelefone.add(telefone);
		}
	}
	
	public void addEndereco(Endereco endereco){
		if(!listaEndereco.contains(endereco)){
			listaEndereco.add(endereco);
		}
	}
	
	public void addPessoa(Pessoa pessoaRelacionada){
		if(!this.listaPessoaRelacionada.contains(pessoaRelacionada)){
			this.listaPessoaRelacionada.add(pessoaRelacionada);
		}
	}
	
	public void removerEndereco(Endereco endereco){
		this.listaEndereco.remove(endereco);
	}
	
	public void removerTelefone(Telefone telefone){
		this.listaTelefone.remove(telefone);
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getNomePessoa() {
		return nomePessoa;
	}




	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}




	public String getRg() {
		return rg;
	}




	public void setRg(String rg) {
		this.rg = rg;
	}




	public String getOrgaoExpeditor() {
		return orgaoExpeditor;
	}




	public void setOrgaoExpeditor(String orgaoExpeditor) {
		this.orgaoExpeditor = orgaoExpeditor;
	}




	public String getNaturalidade() {
		return naturalidade;
	}




	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}




	public String getCpf() {
		return cpf;
	}




	public void setCpf(String cpf) {
		this.cpf = cpf;
	}




	public String getNascionalidade() {
		return nascionalidade;
	}




	public void setNascionalidade(String nascionalidade) {
		this.nascionalidade = nascionalidade;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	public String getProfissao() {
		return profissao;
	}




	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}




	public String getIndicacao() {
		return indicacao;
	}




	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}




	public String getCertidaoNascimento() {
		return certidaoNascimento;
	}




	public void setCertidaoNascimento(String certidaoNascimento) {
		this.certidaoNascimento = certidaoNascimento;
	}




	public String getInformacoesSobreIntituicao() {
		return informacoesSobreIntituicao;
	}




	public void setInformacoesSobreIntituicao(String informacoesSobreIntituicao) {
		this.informacoesSobreIntituicao = informacoesSobreIntituicao;
	}




	public EnumSexo getSexo() {
		return sexo;
	}




	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}




	public EnumEscolaridade getEscolaridade() {
		return escolaridade;
	}




	public void setEscolaridade(EnumEscolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}




	public EnumTipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}




	public void setTipoPessoa(EnumTipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}




	public EnumEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}




	public void setEstadoCivil(EnumEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}




	public EnumTipoCasa getTipoMoradia() {
		return tipoMoradia;
	}




	public void setTipoMoradia(EnumTipoCasa tipoMoradia) {
		this.tipoMoradia = tipoMoradia;
	}




	public String getNecessidadesEspeciais() {
		return necessidadesEspeciais;
	}




	public void setNecessidadesEspeciais(String necessidadesEspeciais) {
		this.necessidadesEspeciais = necessidadesEspeciais;
	}




	public EnumFiliacao getFiliacao() {
		return filiacao;
	}




	public void setFiliacao(EnumFiliacao filiacao) {
		this.filiacao = filiacao;
	}




	public EnumSexo getEnumSexo() {
		return enumSexo;
	}




	public void setEnumSexo(EnumSexo enumSexo) {
		this.enumSexo = enumSexo;
	}




	public EnumEscolaridade getEnumEscolaridade() {
		return enumEscolaridade;
	}




	public void setEnumEscolaridade(EnumEscolaridade enumEscolaridade) {
		this.enumEscolaridade = enumEscolaridade;
	}




	public EnumTipoTelefone getEnumTipoPessoa() {
		return enumTipoPessoa;
	}




	public void setEnumTipoPessoa(EnumTipoTelefone enumTipoPessoa) {
		this.enumTipoPessoa = enumTipoPessoa;
	}




	public Date getDataExpedicao() {
		return dataExpedicao;
	}




	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}




	public Date getDataNascimento() {
		return dataNascimento;
	}




	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}




	public Date getDataVencimento() {
		return dataVencimento;
	}




	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}




	public BigDecimal getValorBeneficio() {
		return valorBeneficio;
	}




	public void setValorBeneficio(BigDecimal valorBeneficio) {
		this.valorBeneficio = valorBeneficio;
	}




	public BigDecimal getRendaOutra() {
		return rendaOutra;
	}




	public void setRendaOutra(BigDecimal rendaOutra) {
		this.rendaOutra = rendaOutra;
	}




	public BigDecimal getRendaPai() {
		return rendaPai;
	}




	public void setRendaPai(BigDecimal rendaPai) {
		this.rendaPai = rendaPai;
	}




	public BigDecimal getRendaMae() {
		return rendaMae;
	}




	public void setRendaMae(BigDecimal rendaMae) {
		this.rendaMae = rendaMae;
	}




	public BigDecimal getRendaResponsavel() {
		return rendaResponsavel;
	}




	public void setRendaResponsavel(BigDecimal rendaResponsavel) {
		this.rendaResponsavel = rendaResponsavel;
	}




	public BigDecimal getValorAluguel() {
		return valorAluguel;
	}




	public void setValorAluguel(BigDecimal valorAluguel) {
		this.valorAluguel = valorAluguel;
	}




	public BigDecimal getValorInicial() {
		return valorInicial;
	}




	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}




	public BigDecimal getValorFinal() {
		return valorFinal;
	}




	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}




	public Integer getNumeroDePessoasMoradia() {
		return numeroDePessoasMoradia;
	}




	public void setNumeroDePessoasMoradia(Integer numeroDePessoasMoradia) {
		this.numeroDePessoasMoradia = numeroDePessoasMoradia;
	}




	public Integer getNumeroFilhosMatriculados() {
		return numeroFilhosMatriculados;
	}




	public void setNumeroFilhosMatriculados(Integer numeroFilhosMatriculados) {
		this.numeroFilhosMatriculados = numeroFilhosMatriculados;
	}




	public Boolean getPaisVivemJuntos() {
		return paisVivemJuntos;
	}




	public void setPaisVivemJuntos(Boolean paisVivemJuntos) {
		this.paisVivemJuntos = paisVivemJuntos;
	}




	public Boolean getRequisitouOutraVaga() {
		return requisitouOutraVaga;
	}




	public void setRequisitouOutraVaga(Boolean requisitouOutraVaga) {
		this.requisitouOutraVaga = requisitouOutraVaga;
	}




	public Boolean getRecebeBeneficio() {
		return recebeBeneficio;
	}




	public void setRecebeBeneficio(Boolean recebeBeneficio) {
		this.recebeBeneficio = recebeBeneficio;
	}




	public Boolean getRequisitouVagaParaOutraCriancao() {
		return requisitouVagaParaOutraCriancao;
	}




	public void setRequisitouVagaParaOutraCriancao(
	      Boolean requisitouVagaParaOutraCriancao) {
		this.requisitouVagaParaOutraCriancao = requisitouVagaParaOutraCriancao;
	}




	public List<Endereco> getListaEndereco() {
		return listaEndereco;
	}




	public List<Pessoa> getListaPessoaRelacionada() {
		return listaPessoaRelacionada;
	}




	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}





	public void setTotalRenda(BigDecimal totalRenda) {
		this.totalRenda = totalRenda;
	}
	

	public void setListaEndereco(List<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}
	
	

}