/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import draftform.Curve;
import draftform.Draftform;
import draftform.Vec2;
import draftform.Vertex;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import tools.ArcTool;
import tools.BezierTool;
import tools.PenTool;
import tools.PolygonTool;
import tools.RotateTool;
import tools.SelectTool;
import tools.Toolkit;
import tools.VertexTool;

/**
 *
 * @author Nyrmburk
 */
public class SketchWindow extends javax.swing.JFrame {

    private Draftform draftform = new Draftform();
    private Toolkit toolkit = new Toolkit(draftform);
    
    private final SelectTool SELECT = new SelectTool();
    private final VertexTool VERTEX = new VertexTool();
    private final BezierTool BEZIER = new BezierTool();
    private final PolygonTool POLYGON = new PolygonTool(10);
    private final RotateTool ROTATE = new RotateTool();
    private final ArcTool ARC = new ArcTool();
    private final PenTool PEN = new PenTool();
    
    /**
     * Creates new form SketchWindow
     */
    public SketchWindow() {
        initComponents();
        jpnlDraw.requestFocus();
        toolkit.setSnapRadius(5);
        toolkit.setSnapToPoints(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jpnlButtons = new javax.swing.JPanel();
        jradSelect = new javax.swing.JRadioButton();
        jradVertex = new javax.swing.JRadioButton();
        jradBezier = new javax.swing.JRadioButton();
        jradArc = new javax.swing.JRadioButton();
        jradPenTool = new javax.swing.JRadioButton();
        jradPolygon = new javax.swing.JRadioButton();
        jradRotate = new javax.swing.JRadioButton();
        jpnlDraw = new javax.swing.JPanel() {

            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.BLACK);
                int pointsize = 7;
                int offset = pointsize/2;
                for (Curve curve : draftform.getCurves()) {

                    g.fillOval(
                        (int) curve.getStart().getX() - offset,
                        (int) curve.getStart().getY() - offset,
                        pointsize, pointsize);
                    g.fillOval(
                        (int) curve.getEnd().getX() - offset,
                        (int) curve.getEnd().getY() - offset,
                        pointsize, pointsize);

                    Vec2[] points = curve.linearize(curve.recommendedSubdivisions());

                    for (int i = 1; i < points.length; i++) {

                        g.drawLine(
                            (int) points[i-1].getX(),
                            (int) points[i-1].getY(),
                            (int) points[i].getX(),
                            (int) points[i].getY());
                    }

                    points = curve.getControlPoints();

                    for (int i = 0; i < points.length; i++) {

                        g.drawOval(
                            (int) points[i].getX() - offset,
                            (int) points[i].getY() - offset,
                            pointsize, pointsize);
                    }
                }

                for (Vertex vert : draftform.getVerts()) {

                    g.fillOval(
                        (int) vert.getX() - offset,
                        (int) vert.getY() - offset,
                        pointsize, pointsize);
                }

                g.setColor(Color.BLUE);
                for (Vertex vert : toolkit.getSelectedVerts()) {

                    g.fillOval(
                        (int) vert.getX() - offset,
                        (int) vert.getY() - offset,
                        pointsize, pointsize);
                }
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);

        jpnlButtons.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnlButtons.setFocusable(false);

        buttonGroup.add(jradSelect);
        jradSelect.setSelected(true);
        jradSelect.setText("Select");
        jradSelect.setFocusable(false);
        jradSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradSelectActionPerformed(evt);
            }
        });

        buttonGroup.add(jradVertex);
        jradVertex.setText("Vertex");
        jradVertex.setFocusable(false);
        jradVertex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradVertexActionPerformed(evt);
            }
        });

        buttonGroup.add(jradBezier);
        jradBezier.setText("Bezier");
        jradBezier.setFocusable(false);
        jradBezier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradBezierActionPerformed(evt);
            }
        });

        buttonGroup.add(jradArc);
        jradArc.setText("Arc");
        jradArc.setFocusable(false);
        jradArc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradArcActionPerformed(evt);
            }
        });

        buttonGroup.add(jradPenTool);
        jradPenTool.setText("Pen");
        jradPenTool.setFocusable(false);
        jradPenTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradPenToolActionPerformed(evt);
            }
        });

        buttonGroup.add(jradPolygon);
        jradPolygon.setText("Polygon");
        jradPolygon.setFocusable(false);
        jradPolygon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradPolygonActionPerformed(evt);
            }
        });

        buttonGroup.add(jradRotate);
        jradRotate.setText("Rotate");
        jradRotate.setFocusable(false);
        jradRotate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradRotateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlButtonsLayout = new javax.swing.GroupLayout(jpnlButtons);
        jpnlButtons.setLayout(jpnlButtonsLayout);
        jpnlButtonsLayout.setHorizontalGroup(
            jpnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jradSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jradVertex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jradBezier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jradArc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jradPenTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jradPolygon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jradRotate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlButtonsLayout.setVerticalGroup(
            jpnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jradSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jradVertex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jradBezier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jradArc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jradPenTool)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jradPolygon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jradRotate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlDraw.setBackground(new java.awt.Color(255, 255, 255));
        jpnlDraw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnlDraw.setPreferredSize(new java.awt.Dimension(640, 480));
        jpnlDraw.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpnlDrawMouseDragged(evt);
            }
        });
        jpnlDraw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpnlDrawMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpnlDrawMouseReleased(evt);
            }
        });
        jpnlDraw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jpnlDrawKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpnlDrawLayout = new javax.swing.GroupLayout(jpnlDraw);
        jpnlDraw.setLayout(jpnlDrawLayout);
        jpnlDrawLayout.setHorizontalGroup(
            jpnlDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        jpnlDrawLayout.setVerticalGroup(
            jpnlDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlDraw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpnlDraw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jradSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradSelectActionPerformed
        toolkit.setTool(SELECT);
    }//GEN-LAST:event_jradSelectActionPerformed

    private void jradVertexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradVertexActionPerformed
        toolkit.setTool(VERTEX);
    }//GEN-LAST:event_jradVertexActionPerformed

    private void jradBezierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradBezierActionPerformed
        toolkit.setTool(BEZIER);
    }//GEN-LAST:event_jradBezierActionPerformed

    private void jradArcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradArcActionPerformed
        toolkit.setTool(ARC);
    }//GEN-LAST:event_jradArcActionPerformed

    private void jradPenToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradPenToolActionPerformed
        toolkit.setTool(PEN);
    }//GEN-LAST:event_jradPenToolActionPerformed

    
    private void jpnlDrawMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlDrawMouseDragged

//    	System.out.println(evt);
//    	System.out.println(Integer.toBinaryString(evt.getModifiers()));
//    	System.out.println(Integer.toBinaryString(evt.getModifiers() & (MouseEvent.BUTTON1 << 4)));
    	
    	//don't ask why. I don't know. If you really must know, look at the comment above.
    	if ((evt.getModifiers() & (MouseEvent.BUTTON1 << 4)) == 0)
    		return;
    	
        toolkit.modify(new Vec2(evt.getPoint().x, evt.getPoint().y));
        jpnlDraw.repaint();
    }//GEN-LAST:event_jpnlDrawMouseDragged

    private void jpnlDrawMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlDrawMousePressed

//    	System.out.println(evt);
//    	System.out.println(Integer.toBinaryString(evt.getModifiers()));
//    	System.out.println(Integer.toBinaryString(evt.getModifiers() & (MouseEvent.BUTTON1 << 4)));
    	
    	//don't ask why. I don't know. If you really must know, look at the comment above.
    	if ((evt.getModifiers() & (MouseEvent.BUTTON1 << 4)) == 0) {
            toolkit.resetTool();
            return;
        }
        
        SELECT.setSingleSelection(!evt.isShiftDown());
        
        toolkit.start(new Vec2(evt.getPoint().x, evt.getPoint().y));
        jpnlDraw.repaint();
    }//GEN-LAST:event_jpnlDrawMousePressed

    private void jpnlDrawMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlDrawMouseReleased
    	
//    	System.out.println(evt);
//    	System.out.println(Integer.toBinaryString(evt.getModifiers()));
//    	System.out.println(Integer.toBinaryString(evt.getModifiers() & (MouseEvent.BUTTON1 << 4)));
    	
    	//don't ask why. I don't know. If you really must know, look at the comment above.
    	if ((evt.getModifiers() & (MouseEvent.BUTTON1 << 4)) == 0)
    		return;
    	
        toolkit.end();
    }//GEN-LAST:event_jpnlDrawMouseReleased

    private void jpnlDrawKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpnlDrawKeyTyped
        
        if (evt.getKeyChar() == KeyEvent.VK_DELETE)
            toolkit.removeSelection();
        
        repaint();
    }//GEN-LAST:event_jpnlDrawKeyTyped

    private void jradPolygonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradPolygonActionPerformed
        toolkit.setTool(POLYGON);
    }//GEN-LAST:event_jradPolygonActionPerformed

    private void jradRotateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradRotateActionPerformed
        toolkit.setTool(ROTATE);
    }//GEN-LAST:event_jradRotateActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SketchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SketchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SketchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SketchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SketchWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JPanel jpnlButtons;
    private javax.swing.JPanel jpnlDraw;
    private javax.swing.JRadioButton jradArc;
    private javax.swing.JRadioButton jradBezier;
    private javax.swing.JRadioButton jradPenTool;
    private javax.swing.JRadioButton jradPolygon;
    private javax.swing.JRadioButton jradRotate;
    private javax.swing.JRadioButton jradSelect;
    private javax.swing.JRadioButton jradVertex;
    // End of variables declaration//GEN-END:variables
}
