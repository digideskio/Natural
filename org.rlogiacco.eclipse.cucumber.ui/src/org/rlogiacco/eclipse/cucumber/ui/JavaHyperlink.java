package org.rlogiacco.eclipse.cucumber.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class JavaHyperlink implements IHyperlink {

	private IJavaElement javaElement;
	private IRegion region;
	private String text;

	public JavaHyperlink(String text, IJavaElement javaElement, IRegion region) {
		super();
		this.javaElement = javaElement;
		this.region = region;
		this.text = text;
	}

	@Override
	public String getHyperlinkText() {
		return text;
	}

	@Override
	public IRegion getHyperlinkRegion() {
		return region;
	}

	@Override
	public String getTypeLabel() {
		return javaElement.getElementName();
	}

	@Override
	public void open() {
		IEditorPart editorPart;
		try {
			editorPart = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) javaElement.getUnderlyingResource(), true);
			JavaUI.revealInEditor(editorPart, javaElement);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}

}