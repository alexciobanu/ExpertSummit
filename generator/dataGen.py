#!/usr/bin/python

import socket
import sys
import time

TCP_IP = 'quickstart'
TCP_PORT = 12345

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))

with open('data','r') as f:
	for line in f:
		s.send(line)
		#print line
		time.sleep(1)
	f.close()
s.close()
