/**
 * 
 */
package net.wyun.dianxiao.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author michael
 *
 */
@Embeddable
public class ATC1PK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1871045733761785998L;

	@Column(name="AbsEntry")
	int absEntry; // `AbsEntry` INT NOT NULL,
	@Column(name="Line")
	int line;

	public ATC1PK() {
		// Your class must have a no-arq constructor
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ATC1PK) {
			ATC1PK pk = (ATC1PK) obj;

			if (pk.getAbsEntry() != absEntry) {
				return false;
			}

			if (pk.line != line) {
				return false;
			}

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		String str = ("" + absEntry) + line;
		return Integer.parseInt(str);
	}

	public int getAbsEntry() {
		return absEntry;
	}

	public void setAbsEntry(int absEntry) {
		this.absEntry = absEntry;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

}
