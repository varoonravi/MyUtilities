package com.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelAndProcess {
	
	public static void main(String[] args)
	{
		List<String> lines = null;
		try 
		{
			lines = readXcelFile("/Users/reverie/Desktop/ChannelsContentDump.xlsx");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(lines != null)
		{
			for(String line : lines)
			{
				System.out.println(line.split("\t")[1]+" - "+line.split("\t")[2]);
			}
		}
	}
	
	public static List<String> readXcelFile(String filePath) throws IOException, InvalidFormatException
	{
		OPCPackage pkg = OPCPackage.open(new File(filePath)); 
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		XSSFSheet sheet  = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();
		List<String> lines = new LinkedList<String>();
		
		while(rows.hasNext())
		{
			row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			StringBuilder line = new StringBuilder();
			while(cells.hasNext())
			{
				cell = (XSSFCell) cells.next();
				if(cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
//					System.out.print(cell.getStringCellValue()+"\t");
					line.append(cell.getStringCellValue());
					line.append("\t");
				}
				else
				{
//					System.out.print(cell.getNumericCellValue()+"\t");
					line.append(cell.getNumericCellValue());
					line.append("\t");
				}
			}
			lines.add(line.toString());
			
			System.out.println();
		}
		return lines;
	}
}
