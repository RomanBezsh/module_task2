package ad251.bezshyiko.ui;

import ad251.bezshyiko.model.ResultRecord;
import ad251.bezshyiko.util.FormatUtils;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChartPanel extends JPanel {

    private static final Color[] BAR_COLORS = {
            new Color(52, 120, 246),
            new Color(46, 184, 114),
            new Color(245, 158, 11),
            new Color(225, 29, 72),
            new Color(124, 58, 237),
            new Color(14, 165, 233)
    };

    private List<ResultRecord> records = Collections.emptyList();

    public ChartPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(480, 340));
    }

    public void setRecords(List<ResultRecord> records) {
        this.records = new ArrayList<>(records);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        g2.setColor(new Color(45, 52, 71));
        g2.setFont(new Font("SansSerif", Font.BOLD, 15));
        FontMetrics titleMetrics = g2.getFontMetrics();
        String title = "Діаграма результатів розрахунків";
        g2.drawString(title, (width - titleMetrics.stringWidth(title)) / 2, 24);

        if (records.isEmpty()) {
            g2.setColor(new Color(130, 136, 150));
            g2.setFont(new Font("SansSerif", Font.PLAIN, 13));
            FontMetrics emptyMetrics = g2.getFontMetrics();
            String message = "Немає даних для побудови діаграми";
            g2.drawString(message, (width - emptyMetrics.stringWidth(message)) / 2, height / 2);
            g2.dispose();
            return;
        }

        int left = 70;
        int right = 24;
        int top = 50;
        int bottom = 56;
        int plotWidth = width - left - right;
        int plotHeight = height - top - bottom;
        if (plotWidth < 60 || plotHeight < 60) {
            g2.dispose();
            return;
        }

        double min = 0.0;
        double max = 0.0;
        for (ResultRecord record : records) {
            min = Math.min(min, record.getResultValue());
            max = Math.max(max, record.getResultValue());
        }
        if (max == min) {
            max += 1.0;
            min -= 1.0;
        }
        double range = max - min;

        g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
        int gridLines = 5;
        for (int i = 0; i <= gridLines; i++) {
            int y = top + plotHeight - (int) Math.round(plotHeight * (double) i / gridLines);
            double value = min + range * i / gridLines;
            g2.setColor(new Color(228, 231, 237));
            g2.drawLine(left, y, left + plotWidth, y);
            g2.setColor(new Color(96, 102, 117));
            String label = FormatUtils.format(value);
            int labelWidth = g2.getFontMetrics().stringWidth(label);
            g2.drawString(label, left - 8 - labelWidth, y + 4);
        }

        int zeroY = top + plotHeight - (int) Math.round((0.0 - min) / range * plotHeight);
        g2.setColor(new Color(160, 166, 180));
        g2.drawLine(left, zeroY, left + plotWidth, zeroY);

        double slot = (double) plotWidth / records.size();
        int barWidth = (int) Math.max(8, Math.min(58, slot * 0.6));
        for (int i = 0; i < records.size(); i++) {
            ResultRecord record = records.get(i);
            double value = record.getResultValue();
            int barHeight = (int) Math.round(Math.abs(value) / range * plotHeight);
            int x = left + (int) Math.round(slot * i + (slot - barWidth) / 2.0);
            int y = value >= 0 ? zeroY - barHeight : zeroY;
            g2.setColor(BAR_COLORS[i % BAR_COLORS.length]);
            g2.fillRoundRect(x, y, barWidth, Math.max(barHeight, 1), 6, 6);

            g2.setFont(new Font("SansSerif", Font.BOLD, 10));
            String valueText = FormatUtils.format(value);
            int valueWidth = g2.getFontMetrics().stringWidth(valueText);
            int valueY = value >= 0 ? y - 5 : y + barHeight + 13;
            g2.setColor(new Color(45, 52, 71));
            g2.drawString(valueText, x + (barWidth - valueWidth) / 2, valueY);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
            String numberText = "№" + record.getNumber();
            int numberWidth = g2.getFontMetrics().stringWidth(numberText);
            g2.setColor(new Color(96, 102, 117));
            g2.drawString(numberText, x + (barWidth - numberWidth) / 2, top + plotHeight + 20);
        }

        g2.setColor(new Color(130, 136, 150));
        g2.drawLine(left, top, left, top + plotHeight);
        g2.drawLine(left, top + plotHeight, left + plotWidth, top + plotHeight);
        g2.dispose();
    }
}
