/*De acordo com o padrão de projeto DAO essa classe vai especificar os métodos
que deverão ser obrigatoriamente implementados
portanto serão as operações CRUD do BD.
 */
package dao;
import java.util.List;

public interface IEntityDAO <T>{

	public void save(T t);
	public T findById(Integer id);
	public List<T> findAll();
	public void update(T t);
	public void delete(T t);
}