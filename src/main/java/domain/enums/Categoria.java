package domain.enums;

public enum Categoria {

    HATCH(0, "HATCH"),
    SEDAN(1, "SEDAN"),
    SUV(2, "SUV"),
    LUXO(3, "LUXO"),
    CAMINHONETE(3, "CAMINHONETE");

    private Integer codigo;
    private String descricao;

    Categoria(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Categoria toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Categoria x : Categoria.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Categoria inválida");
    }
}
