# University Student Record and Campus Route Management System

**Module:** CIT300 Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 1 (Week 10)
**Coverage:** Weeks 1–9 — Linear Data Structures, Trees, Hashing, and Graphs

## Project Description

A Java console application that manages university student records (using a linked list, stack, queue, BST, and hashing) and models campus locations and connections as a graph, with BFS/DFS traversal.

## Group Members

AK.Sanij Kalees - 23DA2-0683
MI.Amnath Nadha - 23DA2-0905
TM.Hakeem - 23DA2-0759
MR.Rishni Ruzaid - 23DA2-1114

## Project Structure

```
CampusSystem/
├── src/
│   ├── Main.java              Menu-driven console interface
│   ├── Student.java           Student record model
│   ├── StudentLinkedList.java Linked list for student records
│   ├── ActionStack.java       Stack for recent actions / undo history
│   ├── ServiceQueue.java      Queue for student service requests
│   ├── StudentBST.java        BST for organizing/searching students by ID
│   ├── StudentHashTable.java  Hash table for fast student ID search
│   └── CampusGraph.java       Graph for campus locations, connections, BFS/DFS
└── README.md
```

## How to Compile and Run

```bash
javac -d bin src/*.java
java -cp bin Main
```

On Windows PowerShell, if wildcard expansion fails:

```powershell
Get-ChildItem -Path src -Filter *.java | ForEach-Object { $_.FullName } | Out-File -Encoding ascii sources.txt
javac -d bin @sources.txt
java -cp bin Main
```

## Features

- Add, update, delete, search, and display student records
- Linked list storage for all student records
- Stack-based recent actions / history log
- Queue-based student service request handling
- BST for organizing and searching students by Student ID
- Hash table for efficient Student ID lookups
- Graph (adjacency list) representing campus locations and connections
- BFS and DFS traversal of the campus network
- Menu-driven console interface with input validation

