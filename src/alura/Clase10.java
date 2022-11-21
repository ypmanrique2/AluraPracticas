package alura;

import alura.model.Curso;
import alura.model.CursoTipo;

import java.util.*;
import java.util.stream.Collectors;

public class Clase10 {

    public static void main(String[] args) {

        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("curso de fisica", 200));
        cursos.add(new Curso("curso de java 8", 500));
        cursos.add(new Curso("curso de geometria del espacio", 400));
        cursos.add(new Curso("curso de historia universal", 300));

        List<CursoTipo> cursoTipos = new ArrayList<>();
        cursoTipos.add(new CursoTipo("curso de fisica", 200, 1));
        cursoTipos.add(new CursoTipo("curso de java 8", 500, 1));
        cursoTipos.add(new CursoTipo("curso de geometria del espacio", 400, 1));
        cursoTipos.add(new CursoTipo("curso de historia universal", 300,2));

        cursos.sort(Comparator.comparing(Curso::getHoras));

        int suma = cursos.stream().mapToInt(x->x.getHoras()).sum();

        OptionalInt max = cursos.stream().mapToInt(x->x.getHoras()).max();

        boolean validaTodos = cursos.stream().allMatch(x -> x.getHoras() > 200);

        /*Optional<Curso> optionalCurso = cursos.stream().filter(x-> x.getHoras()>600).findFirst();

        if(optionalCurso.isPresent()) {
            System.out.println(optionalCurso.get().getNombre());
        }*/

        //cursos.stream().filter(x->x.getHoras()>300).findFirst().ifPresent(System.out::println);

        Curso curso = cursos.stream().filter(x->x.getHoras()>300).findFirst().orElse(new Curso("No Existe!!!!", 0));
        //System.out.println(curso);

        //cursos.stream().filter(x->x.getHoras()>300).collect(Collectors.toList()).forEach(System.out::println);

        Map<Integer, String> mapaCursos = cursos.stream().filter(x->x.getHoras()>300)
                .collect(Collectors.toMap(Curso::getHoras, Curso::getNombre));


        mapaCursos.forEach((llave, valor) -> System.out.println(valor));

        Map<Integer, List<CursoTipo>> mapaCursosTipos = cursoTipos.stream().filter(x->x.getHoras()>100)
                .collect(Collectors.groupingBy(CursoTipo::getTipo));

        mapaCursosTipos.get(1).forEach(System.out::println);

        mapaCursosTipos.get(2).forEach(System.out::println);
    }
}

