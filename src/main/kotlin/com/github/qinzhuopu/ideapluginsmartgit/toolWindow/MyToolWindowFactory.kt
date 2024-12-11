package com.github.qinzhuopu.ideapluginsmartgit.toolWindow

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import git4idea.GitUtil
import git4idea.repo.GitRepository
import javax.swing.BoxLayout
import javax.swing.JButton

class MyToolWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    override fun shouldBeAvailable(project: Project) = true

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val textArea = JBTextArea()
        val button = JButton("current branch").apply {
            addActionListener {

                var currentBranch = "Var currentBranch"
                val repositories: List<GitRepository> = GitUtil.getRepositoryManager(project).repositories
                if (repositories.isNotEmpty()) {
                    currentBranch = repositories[0].currentBranch.toString()
                }
                textArea.text = currentBranch

            }
        }

        val contentPanel = JBPanel<JBPanel<*>>().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(button)
            add(textArea)
        }
        val content = ContentFactory.getInstance().createContent(contentPanel, null, false)
        toolWindow.contentManager.addContent(content)
    }
}
